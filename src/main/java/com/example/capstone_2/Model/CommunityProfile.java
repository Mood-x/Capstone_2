package com.example.capstone_2.Model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CommunityProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; 


    @NotEmpty(message = "Community Profile should be not empty")
    @Size(min = 3, message = "Community profile name must be more than 2 character")
    @Column(columnDefinition = "varchar(25) not null unique")
    private String name;
    private Integer userId; 
    
    @NotNull
    @Column(columnDefinition = "int default 0")
    private Integer purchasesCount = 0;


    @NotNull
    @Column(columnDefinition = "int default 0")
    private Double totalRevenue = 0.0;

    @NotNull
    @Column(columnDefinition = "int default 0")
    private Integer followersCount = 0;

    @NotNull
    @Column(columnDefinition = "int default 0")
    private Integer followingCount = 0;

    
    private Set<Integer> followers = new HashSet<>(); 
    private Set<Integer> following = new HashSet<>(); 
}
