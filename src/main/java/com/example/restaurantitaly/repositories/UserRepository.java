package com.example.restaurantitaly.repositories;

import com.example.restaurantitaly.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {


    Optional <User> findByUsernameAndPassword(String username , String password);

    User findById(int id);

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);
}
