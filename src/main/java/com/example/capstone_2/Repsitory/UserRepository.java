package com.example.capstone_2.Repsitory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.capstone_2.Model.User;

@Repository
public interface  UserRepository extends JpaRepository<User, Integer>{
    Optional<User> findUserById(Integer id); 
}
