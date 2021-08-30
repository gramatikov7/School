package com.example.restaurantitaly.services.impl;


import com.example.restaurantitaly.domain.entities.User;
import com.example.restaurantitaly.domain.models.service.RoleServiceModel;
import com.example.restaurantitaly.domain.models.service.UserRegisterServiceModel;
import com.example.restaurantitaly.domain.models.service.UserServiceModel;
import com.example.restaurantitaly.repositories.UserRepository;
import com.example.restaurantitaly.services.RoleService;
import com.example.restaurantitaly.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleService roleService;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserServiceImpl(UserRepository userRepository, RoleService roleService,
                           ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.modelMapper = modelMapper;

        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public void register(UserRegisterServiceModel userRegisterServiceModel){

        this.checkIfUserAlreadyExist(userRegisterServiceModel.getUsername(), userRegisterServiceModel.getEmail());


            roleService.seedRolesInDb();

            putRoleOnCurrentUser(userRegisterServiceModel);
            User user = modelMapper.map(userRegisterServiceModel, User.class);

            user.setPassword(bCryptPasswordEncoder.encode(userRegisterServiceModel.getPassword()));

            this.userRepository.save(user);

        }





    @Override
    public UserRegisterServiceModel getUserById(int id) {

        User user = userRepository.findById(id);

        return modelMapper.map(user, UserRegisterServiceModel.class);

    }

    @Override
    public UserServiceModel getUserByUsername(String name) {

        User user = userRepository.findByUsername(name).get();
        return modelMapper.map(user , UserServiceModel.class);


    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }


    private void putRoleOnCurrentUser(UserRegisterServiceModel userRegisterServiceModel) {


        if (userRepository.count() == 0) {

            userRegisterServiceModel.setAuthorities(roleService.findAllRoles());

        } else {

            RoleServiceModel role = roleService.findByAuthority("USER");
            userRegisterServiceModel.setAuthorities(new LinkedHashSet<>());
            userRegisterServiceModel.getAuthorities().add(role);

        }
    }

    @Override
    public boolean checkIfUserAlreadyExist(String username , String email) {

        Optional<User> byUsername = userRepository.findByUsername(username);
        Optional<User> byEmail = userRepository.findByEmail(email);


        return byUsername.isPresent() && byEmail.isPresent();

    }



}
