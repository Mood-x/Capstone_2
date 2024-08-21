package com.example.capstone_2.Repsitory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.capstone_2.Model.ExploreCommunity;

@Repository
public interface ExploreCommunityRepository extends JpaRepository<ExploreCommunity, Integer>{
    Optional<ExploreCommunity> findExploreCommunityById(Integer id); 
}
