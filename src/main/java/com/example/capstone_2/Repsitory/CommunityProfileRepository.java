package com.example.capstone_2.Repsitory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.capstone_2.Model.CommunityProfile;

@Repository
public interface CommunityProfileRepository extends JpaRepository<CommunityProfile, Integer>{
    Optional<CommunityProfile> findCommunityProfileById(Integer id);
    Optional<CommunityProfile> findCommunityProfileByUserId(Integer id); 
}
