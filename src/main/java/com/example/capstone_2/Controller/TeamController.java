package com.example.capstone_2.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.capstone_2.API.ApiResponse;
import com.example.capstone_2.Model.Team;
import com.example.capstone_2.Service.TeamService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/teams")
@RequiredArgsConstructor
public class TeamController {

    private final TeamService teamService; 


    // ========================= GET ALL TEAMS =========================
    @GetMapping
    public ResponseEntity getTeams(){
        return ResponseEntity.ok(teamService.getTeams()); 
    }


    // ========================= GET TEAM BY ID =========================
    @GetMapping("{id}")
    public ResponseEntity getTeamById(@PathVariable Integer id){
        Team team = teamService.getTeamById(id); 
        return ResponseEntity.ok(team); 
    }


    // ========================= CREATE NEW TEAM =========================
    @PostMapping
    public ResponseEntity createTeam(@RequestParam Integer id, @Valid @RequestBody Team team){
        teamService.createTeam(id, team);
        return ResponseEntity.ok(new ApiResponse("Create team successfuly")); 
    }


    // ========================= UPDATE TEAM =========================
    @PutMapping
    public ResponseEntity updateTeam(@RequestParam Integer id, @Valid @RequestBody Team team){
        teamService.updateTeam(id, team);
        return ResponseEntity.ok(new ApiResponse("Update team successfuly")); 
    }


    // ========================= DELETE TEAM =========================
    @DeleteMapping
    public ResponseEntity deleteTeam(@RequestParam Integer id){
        teamService.deleteTeam(id);
        return ResponseEntity.ok(new ApiResponse("Delete team successfuly")); 
    }

    @PostMapping("/add/{teamId}/{userId}")
    public ResponseEntity addUserToTeam(@PathVariable Integer teamId, @PathVariable Integer userId){
        teamService.addUserToTeam(userId, teamId);
        return ResponseEntity.ok( new ApiResponse("User added to team successfuly")); 
    }

    @DeleteMapping("/remove/{teamId}/{userId}")
    public ResponseEntity removeUserToTeam(@PathVariable Integer teamId, @PathVariable Integer userId){
        teamService.removeFromTeam(teamId, userId);
        return ResponseEntity.ok( new ApiResponse("User removed from team successfuly")); 
    }

    @DeleteMapping("/leave/{teamId}/{userId}/{password}")
    public ResponseEntity leaveFromTeam(@PathVariable Integer teamId, @PathVariable Integer userId, @PathVariable String password){
        teamService.leaveFromTeam(teamId, userId, password);
        return ResponseEntity.ok( new ApiResponse("User removed from team successfuly")); 
    }
}
