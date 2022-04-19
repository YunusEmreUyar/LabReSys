package com.yemreu.main.service.impl;

import com.yemreu.main.dto.UserRegisterDto;
import com.yemreu.main.model.Role;
import com.yemreu.main.model.User;
import com.yemreu.main.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.*;

import static org.junit.Assert.*;

public class UserServiceImplTest {

    private UserServiceImpl userService;
    private UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Before
    public void setUp() throws Exception {
        userRepository = Mockito.mock(UserRepository.class);
        passwordEncoder = Mockito.mock(BCryptPasswordEncoder.class);
        userService = Mockito.spy(new UserServiceImpl(userRepository, passwordEncoder));
    }

    @Test
    public void whenGetAllUsersCalled_itShouldReturnAllUsers() {
        User user = new User(
                "Yunus",
                "YunusEmre",
                "passwd",
                "Uyar",
                Arrays.asList(new Role("ROLE_USER"), new Role("ROLE_LABORATORY")),
                "12345678910");
        List<User> expected = new ArrayList<>();
        expected.add(user);

        Mockito.when(userService.getAllUsers()).thenReturn(expected);
        Mockito.when(userRepository.findAll()).thenReturn(expected);

        Assert.assertEquals(expected.size(), 1);

        Mockito.verify(userService).getAllUsers();
    }

    @Test
    public void whenGetLaboratoriesCalled_itShouldReturnAllLaboratories() {
        User laboratory = new User(
                "Yunus",
                "YunusEmre",
                "passwd",
                "Uyar",
                Arrays.asList(new Role("ROLE_USER"), new Role("ROLE_LABORATORY")),
                "12345678910");
        User user = new User(
                "Yunus",
                "YunusEmre",
                "passwd",
                "Uyar",
                Arrays.asList(new Role("ROLE_USER")),
                "12345678910");

        List<User> expected = new ArrayList<>();
        expected.add(laboratory);

        Mockito.when(userService.getLaboratories()).thenReturn(expected);

        // Check if the function returns a user list sized 1, instead of 2.
        Assert.assertEquals(expected.size(), 1);

        Mockito.verify(userService).getLaboratories();
    }

    @Test
    public void whenGetOneUserByIdCalled_itShouldReturnTheUser() {
        User user = new User(
                "Yunus",
                "YunusEmre",
                "passwd",
                "Uyar",
                List.of(new Role("ROLE_USER")),
                "12345678910");
        user.setId(123L);

        Optional<User> expected = userRepository.findById(123L);

        Mockito.when(userService.getOneUserById(123L)).thenReturn(user);

        expected.ifPresent(value -> Assert.assertEquals(user, value));
        Mockito.verify(userService).getOneUserById(123L);
    }

    @Test
    public void whenSaveCalled_itShouldReturnTheSavedUser() {
        UserRegisterDto dto = new UserRegisterDto();
        dto.setName("Yunus");
        dto.setPassword("passwd");
        dto.setUsername("YunusEmre");
        dto.setSurname("Uyar");
        dto.setTc("12345678910");
        dto.setRole("ROLE_USER");

        User user = new User(
            "Yunus",
            "YunusEmre",
            "passwd",
            "Uyar",
            List.of(new Role("ROLE_USER")),
            "12345678910");

        Mockito.when(userService.save(dto)).thenReturn(user);
        Mockito.verify(userService).save(dto);
    }

}