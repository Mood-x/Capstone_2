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
import com.example.capstone_2.Model.CommunityProfile;
import com.example.capstone_2.Model.Project;
import com.example.capstone_2.Service.CommunityProfileService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/community-profiles")
@RequiredArgsConstructor
public class CommunityProfileController {

    private final CommunityProfileService communityProfileService; 

    // ========================= GET ALL COMMUNITY PROFILES =========================
    @GetMapping
    public ResponseEntity getCommunityProfiles(){
        return ResponseEntity.ok(communityProfileService.getCommunityProfiles()); 
    }


    // ========================= GET COMMUNITY PROFILE BY ID =========================
    @GetMapping("/{id}")
    public ResponseEntity getCommunityProfileByID(@PathVariable Integer id){
        return ResponseEntity.ok(communityProfileService.getCommunityProfileByID(id)); 
    }


    // ========================= CREATE NEW COMMUNITY PROFILE =========================
    @PostMapping
    public ResponseEntity createCommunityProfile(@Valid @RequestBody CommunityProfile communityProfile){
        communityProfileService.createCommunityProfile(communityProfile);
        return ResponseEntity.ok(new ApiResponse("Create community profile successfuly")); 
    }


    // ========================= UPDATE COMMUNITY PROFILE =========================
    @PutMapping
    public ResponseEntity updateCommunityProfile(@RequestParam Integer id, @Valid @RequestBody CommunityProfile communityProfile){
        communityProfileService.updateCommunityProfile(id, communityProfile);
        return ResponseEntity.ok(new ApiResponse("Update community profile successfuly")); 
    }


    // ========================= DELETE COMMUNITY PROFILE =========================
    @DeleteMapping
    public ResponseEntity deleteCommunityProfile(@RequestParam Integer id){
        communityProfileService.deleteCommunityProfile(id);
        return ResponseEntity.ok(new ApiResponse("Delete community profile successfuly")); 
    }

    // =============================================================================
    @PostMapping("/{userId}/projects")
    public ResponseEntity addProjectToProfile(@PathVariable Integer userId, @RequestBody Project project){
        communityProfileService.addProjectToProfile(userId, project);
        return ResponseEntity.ok("Project added to Community Profile"); 
    }

    // 
    @PostMapping("/{profileId}/follow")
    public ResponseEntity followUser(@PathVariable Integer profileId, @RequestParam Integer followerId){
        communityProfileService.followUser(profileId, followerId);
        return ResponseEntity.ok(new ApiResponse("Follow")); 
    }

    @PostMapping("/{profileId}/unfollow")
    public ResponseEntity unfollowUser(@PathVariable Integer profileId, @RequestParam Integer followerId){
        communityProfileService.unfollowUser(profileId, followerId);
        return ResponseEntity.ok(new ApiResponse("Unfollow")); 
    }
}
