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
import com.example.capstone_2.Model.Plan;
import com.example.capstone_2.Service.PlanService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/plans")
@RequiredArgsConstructor
public class PlanController {

    private final PlanService planService; 

    // ========================= GET ALL PLANS =========================
    @GetMapping
    public ResponseEntity getPlans(){
        return ResponseEntity.ok(planService.getPlans()); 
    }


    // ========================= GET PLAN BY TYPE =========================
    @GetMapping("/{planType}")
    public ResponseEntity getPlanByType(@PathVariable Plan.PlanType planType){
        return ResponseEntity.ok(planService.getPlanByType(planType)); 
    }


    // ========================= CREATE NEW PLAN =========================
    @PostMapping
    public ResponseEntity createPlan(@Valid @RequestBody Plan plan){
        planService.createPlan(plan);
        return ResponseEntity.ok(new ApiResponse("Create plan successfuly")); 
    }


    // ========================= UPDATE PLAN =========================
    @PutMapping
    public ResponseEntity deletePlan(@RequestParam Plan.PlanType planType, @Valid @RequestBody Plan plan){
        planService.updatePlan(planType, plan);
        return ResponseEntity.ok(new ApiResponse("Update plan successfuly")); 
    }


    // ========================= DELETE PLAN =========================
    @DeleteMapping
    public ResponseEntity deletePlan(@RequestParam Plan.PlanType planType){
        planService.deletePlan(planType);
        return ResponseEntity.ok(new ApiResponse("Delete plan successfuly")); 
    }


}
