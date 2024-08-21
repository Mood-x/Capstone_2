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
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; 

    @NotEmpty(message = "Team name should be not emtpy")
    @Size(min = 3, max = 25, message = "Team name must be between 3 to 25 characters")
    @Column(columnDefinition = "varchar(25) not null")
    private String name;

    // @NotEmpty
    // @Size
    @Column(columnDefinition = "varchar(25) not null")
    private String owner;

    // @NotNull
    private Integer ownerId; 

    private Set<Integer> members = new HashSet<>(); 
}
