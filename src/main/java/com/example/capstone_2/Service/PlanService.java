package com.example.capstone_2.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.capstone_2.API.ApiException;
import com.example.capstone_2.Model.Plan;
import com.example.capstone_2.Repsitory.PlanRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PlanService {

    private final PlanRepository planRepository; 

    // ========================= GET ALL PLAN =========================
    public List<Plan> getPlans(){
        return planRepository.findAll(); 
    }


    // ========================= GET PLAN BY TYPE =========================
    public Plan getPlanByType(Plan.PlanType planType){
        Plan plan = planRepository.findPlanByPlanType(planType)
            .orElseThrow(() -> new ApiException("PLAN NOT FOUND")); 

        return plan; 
    }


    // ========================= CREATE NEW PLAN =========================
    public void createPlan(Plan plan){
        planRepository.save(plan); 
    }


    // ========================= UPDATE PLAN =========================
    public void updatePlan(Plan.PlanType planType, Plan updatePlan){
        Plan plan = planRepository.findPlanByPlanType(planType)
            .orElseThrow(() -> new ApiException("PLAN NOT FOUND")); 
        plan.setPlanType(updatePlan.getPlanType());
        plan.setMaxDraft(updatePlan.getMaxDraft());
        plan.setPrice(updatePlan.getPrice());
        plan.setActive(updatePlan.isActive()); 
        plan.setCommunityProfile(updatePlan.isCommunityProfile());
        plan.setCreateTeams(updatePlan.isCreateTeams());
        planRepository.save(plan); 
    }

    
    // ========================= DELTE PLAN  =========================
    public void deletePlan(Plan.PlanType planType){
        Plan plan = planRepository.findPlanByPlanType(planType)
            .orElseThrow(() -> new ApiException("PLAN NOT FOUND")); 
        planRepository.delete(plan); 
    }

    // ================================= Default Plans =================================

    public void init(){
        if (planRepository.findPlanByPlanType(Plan.PlanType.STARTER) == null) {
            Plan starterPlan = new Plan();
            starterPlan.setPlanType(Plan.PlanType.STARTER);
            starterPlan.setPrice(0.0); 
            starterPlan.setMaxDraft(1);
            starterPlan.setActive(false);
            starterPlan.setCommunityProfile(false);
            starterPlan.setCreateTeams(false);
            planRepository.save(starterPlan);
        }
    
        if (planRepository.findPlanByPlanType(Plan.PlanType.PROFESSIONAL) == null) {
            Plan professionalPlan = new Plan();
            professionalPlan.setPlanType(Plan.PlanType.PROFESSIONAL);
            professionalPlan.setPrice(12.0);
            professionalPlan.setMaxDraft(Integer.MAX_VALUE); 
            professionalPlan.setActive(true);
            professionalPlan.setCommunityProfile(true);
            professionalPlan.setCreateTeams(true);
            planRepository.save(professionalPlan);
        }
    }
}
