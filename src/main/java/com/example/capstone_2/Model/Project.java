package com.example.capstone_2.Model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @NotEmpty(message = "Project name should be not empty")
    private String name = "Untitled";


    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('DRAFT', 'PUBLIESHED', PURCHASED)")
    private Status status = Status.DRAFT; 


    @NotNull
    @Positive
    private Integer userId; 


    @NotNull
    @Positive
    private Integer teamId; 


    @NotNull
    @Positive
    private Integer communityProfileId;

    @NotNull
    @PositiveOrZero
    private double price = 0.0; 

    @NotNull
    @PositiveOrZero
    private Integer likes = 0;

    @NotNull
    @PositiveOrZero
    private Integer purchasesCount = 0;

    @NotNull
    private double totalRevnue = 0.0;

    private List<String> comments = new ArrayList<>();
    
    private List<Integer> ratings = new ArrayList<>(); 

    @NotNull
    private Double averageRating = 0.0; 

    @CreationTimestamp
    @Column(name = "createdAt", nullable = false, updatable = false)
    private LocalDateTime createdAt; 

    @UpdateTimestamp
    @Column(name = "updatedAt")
    private LocalDateTime updatedAt;


    public Double calcAverageRating(){
        if(ratings.isEmpty()){
            return 0.0; 
        }
        int total = 0; 
        for(Integer rating : ratings){
            total += rating; 
        }
        return total / (double) ratings.size(); 
    }

    public Double getAverageRating(){
        return averageRating != null ? averageRating : calcAverageRating(); 
    }

    public enum Status{
        DRAFT, 
        PUBLISHED,
        PURCHASED
    }

}
