package com.example.capstone_2.Model;

import java.util.HashSet;
import java.util.Set;

import com.example.capstone_2.Model.Plan.PlanType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotEmpty(message = "Username should be not empty")
    @Size(min = 3, max = 25, message = "Username must be between 3 and 25 characters")
    @Column(length = 25, nullable = false, unique = true)
    private String username; 

    @NotEmpty(message = "Email should be not empty")
    @Email(message = "Invalid email format")
    @Column(length = 25, nullable = false, unique = true)
    private String email; 

    @NotEmpty(message = "Password should be not empty")
    @Size(min = 6, max = 25, message = "Password must be between 6 and 25 characters")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%^&*])[a-zA-Z0-9!@#$%^&*]{6,25}$", message = "Password must be between 6 and 25")
    @Column(length = 25, nullable = false)
    private String password; 

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('USER', 'OWNER')")
    private Role role = Role.USER; 


    @NotNull
    @PositiveOrZero
    @Column(columnDefinition = "double not null default 0.0")
    private double balance = 0.0;

    private PlanType plan = PlanType.STARTER; 

    private Set<Integer> purchaseProjectIds = new HashSet<>(); 

    public enum Role{
        USER, 
        OWNER
    }
}
