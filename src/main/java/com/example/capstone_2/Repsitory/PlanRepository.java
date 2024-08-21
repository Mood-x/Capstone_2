package com.example.capstone_2.Repsitory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.capstone_2.Model.Plan;

@Repository
public interface PlanRepository extends JpaRepository<Plan, Integer>{
    Optional<Plan> findPlanByPlanType(Plan.PlanType planType); 

}
