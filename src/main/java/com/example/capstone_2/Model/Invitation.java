package com.example.capstone_2.Model;


import jakarta.persistence.Column;
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
public class Invitation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; 

    @NotNull(message = "Team Id should be not null")
    @Column(columnDefinition = "int not null")
    private Integer teamId;


    @NotNull(message = "Invited Id should be not null")
    @Column(columnDefinition = "int not null")
    private Integer invitedUserId;


    @NotNull(message = "Inviter Id should be not null")
    @Column(columnDefinition = "int not null")
    private Integer inviterUserId;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "enum('PENDING', 'ACCEPTED', 'REJECTED')")
    private Status status; 

    
    public enum Status{
        PENDING,
        ACCEPTED,
        REJECTED
    }
}
