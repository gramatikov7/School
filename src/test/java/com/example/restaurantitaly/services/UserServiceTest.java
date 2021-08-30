package com.example.restaurantitaly.services;


import com.example.restaurantitaly.domain.entities.User;
import com.example.restaurantitaly.domain.models.service.UserRegisterServiceModel;
import com.example.restaurantitaly.repositories.UserRepository;
import com.example.restaurantitaly.services.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.HashSet;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @Mock
    RoleService roleService;

    @Mock
    BCryptPasswordEncoder encoder;

    @Mock
    ModelMapper modelMapper;

    User user;
    UserRegisterServiceModel userRegisterServiceModel;

    @InjectMocks
    UserServiceImpl service;


    @Before
    public void initTests() {
        user = new User();
        user.setId(1);
        user.setUsername("Alexander");
        user.setFirstName("Alexander");
        user.setLastName("Alexander");
        user.setEmail("alexander@abv.bg");
        user.setPassword("12345");
        user.setAuthorities(new HashSet<>());
        user.setBookings(new HashSet<>());

        userRegisterServiceModel = new UserRegisterServiceModel();
        userRegisterServiceModel.setUsername("Alexander");
        userRegisterServiceModel.setFirstName("Alexander");
        userRegisterServiceModel.setLastName("Alexander");
        userRegisterServiceModel.setEmail("alexander@abv.bg");
        userRegisterServiceModel.setPassword("12345");
        userRegisterServiceModel.setAuthorities(new HashSet<>());
        userRegisterServiceModel.setConfirmPassword("12345");
    }


    @Test
    public void register_Test_When_all_Should_Work() {
        Mockito.when(modelMapper.map(userRegisterServiceModel, User.class))
                .thenReturn(user);
        Mockito.when(userRepository.save(user))
                .thenReturn(user);

        service.register(userRegisterServiceModel);

        Mockito.verify(modelMapper).map(userRegisterServiceModel, User.class);
        Mockito.verify(userRepository).save(user);


    }


    @Test(expected = UsernameNotFoundException.class)
    public void loadUserByUsername_WhenNotUserExist_ShouldThrow() {
        Mockito.when(userRepository.findByUsername("name"))
                .thenThrow(UsernameNotFoundException.class);
        service.loadUserByUsername("name");
    }

    @Test
    public void findByUsername_WhenUserExist_ShouldWork() {
        Mockito.when(userRepository.findByUsername("name"))
                .thenReturn(Optional.of(user));
        User userResult = (User) service.loadUserByUsername("name");
        assertEquals(user, userResult);
    }

    @Test
    public void check_If_User_Exist() {

        Mockito.when(userRepository.findByUsername("Alexander")).thenReturn(Optional.of(user));
        Mockito.when(userRepository.findByEmail("alexander@abv.bg")).thenReturn(Optional.of(user));
        boolean actual = service.checkIfUserAlreadyExist("Alexander", "alexander@abv.bg");


        assertTrue(actual);
    }


    @Test
    public void check_If_User_WithThisUsername_Not_Exist() {

        Mockito.when(userRepository.findByEmail("alexander@abv.bg")).thenReturn(Optional.of(user));
        boolean actual = service.checkIfUserAlreadyExist("Alexdsada", "alexander@abv.bg");


        assertFalse(actual);
    }

    @Test
    public void check_If_User_WithThisEmail_Not_Exist() {

        Mockito.when(userRepository.findByUsername("Alexander")).thenReturn(Optional.of(user));
        boolean actual = service.checkIfUserAlreadyExist("Alexander", "aadsadsa@abv.bg");


        assertFalse(actual);
    }


    @Test
    public void getUserById_IfExist() {

        Mockito.when(userRepository.findById(1)).thenReturn(user);
        Mockito.when(modelMapper.map(user , UserRegisterServiceModel.class)).thenReturn(userRegisterServiceModel);

        UserRegisterServiceModel userById =  service.getUserById(1);

        assertEquals(userRegisterServiceModel , userById);

    }




}