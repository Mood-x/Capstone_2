package com.example.capstone_2.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Plan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; 

    @Enumerated(EnumType.STRING)
    private PlanType planType = PlanType.STARTER;
    
    @NotNull
    private double price;

    @NotNull
    private int maxDraft;

    private boolean isActive;
    private boolean communityProfile; 
    private boolean createTeams; 

    public enum PlanType{
        STARTER, 
        PROFESSIONAL
    }
}
