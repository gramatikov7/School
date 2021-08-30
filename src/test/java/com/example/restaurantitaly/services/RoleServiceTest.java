package com.example.restaurantitaly.services;


import com.example.restaurantitaly.domain.entities.Role;
import com.example.restaurantitaly.domain.models.service.RoleServiceModel;
import com.example.restaurantitaly.repositories.RoleRepository;
import com.example.restaurantitaly.services.impl.RoleServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class RoleServiceTest {

    @Mock
    RoleRepository roleRepository;

    @Mock
    ModelMapper modelMapper;

    @InjectMocks
    RoleServiceImpl service;


    @Test
    public void seedRoles_WhenNoRoles_ShouldSeed4(){
        Mockito.when(roleRepository.count())
                .thenReturn(0L);
        service.seedRolesInDb();

        assertEquals(0, roleRepository.findAll().size());
    }

    @Test
    public void findAll_WhenOneROle_ShouldReturn1(){
        Role role = new Role("test");

        List<Role> roles = new ArrayList<>();
        roles.add(role);

        Mockito.when(roleRepository.findAll())
                .thenReturn(roles);

        int result = service.findAllRoles().size();
        assertEquals(1, result);

    }

    @Test
    public void findByAuthority_WhenOneRoleExist_ShouldWork(){
        RoleServiceModel roleService = new RoleServiceModel();
        Role role = new Role("test");

        Mockito.when(roleRepository.findByAuthority("test"))
                .thenReturn(java.util.Optional.of(role));

        Mockito.when(modelMapper.map(role, RoleServiceModel.class))
                .thenReturn(roleService);

        RoleServiceModel result = service.findByAuthority("test");
        assertEquals(roleService, result);

    }


}

