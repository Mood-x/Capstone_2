package com.example.capstone_2.Controller;

import java.util.List;

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
import com.example.capstone_2.Model.Project;
import com.example.capstone_2.Service.ProjectService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/projects")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService; 

    // ========================= GET ALL PROJECTS =========================
    @GetMapping
    public ResponseEntity getProjects(){
        return ResponseEntity.ok(projectService.getProjects()); 
    }

    // ========================= GET PROJECT BY ID =========================
    @GetMapping("/{id}")
    public ResponseEntity getProjectById(@PathVariable Integer id){
        return ResponseEntity.ok(projectService.getProjectById(id)); 
    }


    // ========================= CREATE NEW PROJECT =========================
    @PostMapping
    public ResponseEntity createProject(@Valid @RequestBody Project project, @RequestParam Integer userId){
        projectService.createProject(project, userId);
        return ResponseEntity.ok(new ApiResponse("Create project successfuly")); 
    }


    // ========================= UPDATE PROJECT =========================
    @PutMapping
    public ResponseEntity updateProject(@RequestParam Integer id, @Valid @RequestBody Project project){
        projectService.updateProject(id, project);
        return ResponseEntity.ok(new ApiResponse("Update project successfuly")); 
    }


    // ========================= DELETE PROJECT =========================
    @DeleteMapping
    public ResponseEntity deleteProject(@RequestParam Integer id){
        projectService.deleteProject(id);
        return ResponseEntity.ok(new ApiResponse("Delete project successfuly")); 
    }


    @PostMapping("/{projectId}/publish-to-team/{teamId}")
    public ResponseEntity publishProjectToTeam(@PathVariable Integer projectId, @PathVariable Integer teamId){
        projectService.publishProjectToTeam(projectId, teamId);
        return ResponseEntity.ok().build(); 
    }


    @GetMapping("/team/published")
    public ResponseEntity getProjectByTeamId(@RequestParam Integer id){
        List<Project> projects = projectService.getProjectsByTeamId(id); 
        return ResponseEntity.ok(projects); 
    }


    // =======================================================

    @PostMapping("/{projectId}/publish-to-profile/{userId}/{price}")
    public ResponseEntity publishProjectToProfile(@PathVariable Integer projectId, @PathVariable Integer userId, @PathVariable double price){
        projectService.publishProjectToCommunityProfile(projectId, userId, price);
        return ResponseEntity.ok().build(); 
    }

    @GetMapping("/profile/published")
    public ResponseEntity getProjectsByProfileId(@RequestParam Integer id){
        List<Project> projects = projectService.getProjectsByProfileId(id); 
        return ResponseEntity.ok(projects); 
    }

    @GetMapping("/published")
    public ResponseEntity getAllPublishedProjects(){
        List<Project> projects = projectService.getAllPublishedProjects();
        return ResponseEntity.ok(projects); 
    }


    @PostMapping("/{projectId}/purchase/{userId}")
    public ResponseEntity purchaseProject(@PathVariable Integer projectId, @PathVariable Integer userId){
        projectService.purchaseProject(projectId, userId);
        return ResponseEntity.ok().build(); 
    }

    //addComment
    @PostMapping("/{projectId}/comments")
    public ResponseEntity addComment(@PathVariable Integer projectId, @RequestBody String comment){
        projectService.addCommentToProject(projectId, comment);
        return ResponseEntity.ok(new ApiResponse("Added comment successfuly")); 
    }

    //addRating
    @PostMapping("/{projectId}/ratings")
    public ResponseEntity addRating(@PathVariable Integer projectId, @RequestParam Integer rating){
        projectService.addRatingToProject(projectId, rating);
        return ResponseEntity.ok(new ApiResponse("Added rating successfuly")); 
    }

    //addLikes
    @PostMapping("/{projectId}/likes")
    public ResponseEntity likeProject(@PathVariable Integer projectId){
        projectService.addLikeToProject(projectId);
        return ResponseEntity.ok(new ApiResponse("Added like successfuly")); 
    }
}
