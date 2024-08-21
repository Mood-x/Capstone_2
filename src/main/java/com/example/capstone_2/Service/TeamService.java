package com.example.capstone_2.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.capstone_2.API.ApiException;
import com.example.capstone_2.Model.Team;
import com.example.capstone_2.Model.User;
import com.example.capstone_2.Repsitory.TeamRepository;
import com.example.capstone_2.Repsitory.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamRepository teamRepository;
    private final UserRepository userRepository; 


    // ========================= GET ALL TEAMS =========================
    public List<Team> getTeams(){
        return teamRepository.findAll(); 
    }


    // ========================= GET TEAM BY ID =========================
    public Team getTeamById(Integer id){
        Team team = teamRepository.findTeamById(id)
            .orElseThrow(() -> new ApiException("TEAM NOT FOUND")); 

        return team; 
    }


    // ========================= CREATE NEW TEAM =========================
    public void createTeam(Integer userId, Team team){
        User user = userRepository.findUserById(userId)
            .orElseThrow(() -> new ApiException("USER NOT FOUND"));

        if(user.getPlan().equals(user.getPlan().PROFESSIONAL)){
            team.setOwnerId(userId);
            team.setOwner(user.getUsername());
            teamRepository.save(team); 
        }else {
            throw new ApiException("USER CANNOT CEREATE TEAM BECAUSE YOU DON SUBSCRIBE TO PROFIESSONAL PLAN"); 
        }
    }


    // ========================= UPDATE TEAM =========================
    public void updateTeam(Integer id, Team updateTeam){
        Team team = teamRepository.findTeamById(id)
            .orElseThrow(() -> new ApiException("TEAM NOT FOUND"));
        team.setName(updateTeam.getName());
        teamRepository.save(team); 
    }


    // ========================= DELETE TEAM =========================
    public void deleteTeam(Integer id){
        Team team = teamRepository.findTeamById(id)
            .orElseThrow(() -> new ApiException("TEAM NOT FOUND")); 
        teamRepository.delete(team);
    }


    // ========================= ADD USER TO TEAM =========================
    public void addUserToTeam(Integer teamId, Integer userId){
        User user = userRepository.findUserById(userId)
            .orElseThrow(() -> new ApiException("USER NOT FOUND")); 

        Team team = teamRepository.findTeamById(teamId)
            .orElseThrow(() -> new ApiException("TEAM NOT FOUND")); 

        if(team.getMembers().contains(userId)){
            throw new ApiException("USER IS ALREADY A MEMBER OF THE TEAM"); 

        }

        if(userId == team.getOwnerId()){
            throw new ApiException("USER CANNOT ADD THEMSELF TO THE THEM"); 
        }
        
        team.getMembers().add(userId);
        teamRepository.save(team); 
    }

    // ========================= REMOVE MEMBER FROM TEAM =========================
    public void removeFromTeam(Integer teamId, Integer userId){
        User user = userRepository.findUserById(userId)
        .orElseThrow(() -> new ApiException("USER NOT FOUND")); 

        Team team = teamRepository.findTeamById(teamId)
            .orElseThrow(() -> new ApiException("TEAM NOT FOUND")); 

        if(userId == team.getOwnerId()){
            throw new ApiException("USER CANNOT REMOVE THEMSELF TO THE THEM"); 
        }

        team.getMembers().remove(userId); 
        teamRepository.save(team); 
    }

    // ========================= LEAVE FROM TEAM =========================
    public void leaveFromTeam(Integer teamId, Integer userId, String password){
        User user = userRepository.findUserById(userId)
        .orElseThrow(() -> new ApiException("USER NOT FOUND")); 

        Team team = teamRepository.findTeamById(teamId)
            .orElseThrow(() -> new ApiException("TEAM NOT FOUND")); 

        if(!user.getPassword().equals(password)){
            throw new ApiException("Password incorect"); 
        }
        
        team.getMembers().remove(userId); 
        teamRepository.save(team); 
    }   
}
