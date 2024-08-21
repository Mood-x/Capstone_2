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
import com.example.capstone_2.Model.ExploreCommunity;
import com.example.capstone_2.Service.ExploreCommunityService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/explore-community")
@RequiredArgsConstructor
public class ExploreCommunityController {

    private final ExploreCommunityService exploreCommunityService; 

    // ========================= GET ALL EXPLORE COMMUNITY =========================
    @GetMapping
    public ResponseEntity getExploreCommunity(){
        return ResponseEntity.ok(exploreCommunityService.getExploreCommunity()); 
    }


    // ========================= GET EXPLORE COMMUNITY BY ID =========================
    @GetMapping("/{id}")
    public ResponseEntity getExploreCommunityByID(@PathVariable Integer id){
        return ResponseEntity.ok(exploreCommunityService.getExploreCommunityByID(id)); 
    }


    // ========================= CREATE NEW EXPLORE COMMUNITY =========================
    @PostMapping
    public ResponseEntity createExploreCommunity(@Valid @RequestBody ExploreCommunity exploreCommunity){
        exploreCommunityService.createExploreCommunity(exploreCommunity);
        return ResponseEntity.ok(new ApiResponse("Create explore community successfuly")); 
    }


    // ========================= UPDATE EXPLORE COMMUNITY =========================
    @PutMapping
    public ResponseEntity updateExploreCommunity(@RequestParam Integer id, @Valid @RequestBody ExploreCommunity exploreCommunity){
        exploreCommunityService.updateExploreCommunity(id, exploreCommunity);;
        return ResponseEntity.ok(new ApiResponse("Update explore community successfuly")); 
    }


    // ========================= DELETE EXPLORE COMMUNITY =========================
    @DeleteMapping
    public ResponseEntity deleteExploreCommunity(@RequestParam Integer id){
        exploreCommunityService.deleteExploreCommunity(id);;
        return ResponseEntity.ok(new ApiResponse("Delete explore community successfuly")); 
    }

}
