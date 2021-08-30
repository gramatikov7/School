package com.example.restaurantitaly.services.impl;

import com.example.restaurantitaly.domain.entities.Role;
import com.example.restaurantitaly.domain.models.service.RoleServiceModel;
import com.example.restaurantitaly.repositories.RoleRepository;
import com.example.restaurantitaly.services.RoleService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final ModelMapper modelMapper;

    public RoleServiceImpl(RoleRepository roleRepository, ModelMapper modelMapper) {
        this.roleRepository = roleRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public void seedRolesInDb() {
        if (roleRepository.count() == 0) {
            roleRepository.saveAndFlush(new Role("ROOT"));
            roleRepository.saveAndFlush(new Role("ADMIN"));
            roleRepository.saveAndFlush(new Role("USER"));
        }
    }

    @Override
    public Set<RoleServiceModel> findAllRoles() {
        List<Role> all = roleRepository.findAll();
        return all.stream().map(a -> modelMapper.map(a, RoleServiceModel.class)).collect(Collectors.toSet());

    }

    @Override
    public RoleServiceModel findByAuthority(String authority) {
        Optional<Role> byAuthority = roleRepository.findByAuthority(authority);
        return byAuthority.map(role -> modelMapper.map(role, RoleServiceModel.class)).orElse(null);
    }
}
