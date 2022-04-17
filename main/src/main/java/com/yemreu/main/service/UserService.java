package com.yemreu.main.service;

import com.yemreu.main.dto.UserRegisterDto;
import com.yemreu.main.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;


public interface UserService extends UserDetailsService {
    User save(UserRegisterDto userRegisterDto);
    List<User> getAllUsers(); // Get All Users
    List<User> getLaboratories(); // Get Laboratories Only.
    User getOneUserById(Long id);
}
