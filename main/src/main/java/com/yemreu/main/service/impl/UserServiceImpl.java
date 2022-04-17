package com.yemreu.main.service.impl;

import com.yemreu.main.dto.UserRegisterDto;
import com.yemreu.main.model.Role;
import com.yemreu.main.model.User;
import com.yemreu.main.repository.UserRepository;
import com.yemreu.main.service.UserService;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, @Lazy BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<User> getAllUsers() {
        // Laboratories has 2 roles, admins 3 and patients 1 only.
        // Returns all users including Laboratories because laboratories can be also a patient.
        return userRepository.findAll();
    }

    @Override
    public List<User> getLaboratories() {
        return userRepository
                .findAll()
                .stream()
                .filter(user -> user.getRoles().size() > 1)
                .collect(Collectors.toList());
    }

    @Override
    public User getOneUserById(Long id) {
        return userRepository.getById(id);
    }

    @Override
    public User save(UserRegisterDto userRegisterDto) {
        // Implement roles.
        List<Role> roles = new ArrayList<Role>();
        roles.add(new Role("ROLE_USER"));
        if (userRegisterDto.getRole().equals("ROLE_LABORATORY")) {
            roles.add(new Role("ROLE_LABORATORY"));
        }

        User user = new User(
                userRegisterDto.getName(),
                userRegisterDto.getUsername(),
                passwordEncoder.encode(userRegisterDto.getPassword()),
                userRegisterDto.getSurname(),
                roles,
                userRegisterDto.getTc()
        );
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or passwd.");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles
            .stream()
            .map(role -> new SimpleGrantedAuthority(role.getName()))
            .collect(Collectors.toList());
    }
}
