package com.example.capstone_2.Repsitory;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.capstone_2.Model.Project;
@Repository
public interface  ProjectRepository extends JpaRepository<Project, Integer>{

    Optional<Project> findProjectById(Integer id); 
    List<Project> findProjectByStatus(Project.Status status);
    Optional<List<Project>> findProjectsByTeamId(Integer teamId);
    Optional<List<Project>> findProjectByCommunityProfileId(Integer communityProfileId);
    // List<Project> findProjectByCommunityProfileId(Integer communityProfileId); 
}
