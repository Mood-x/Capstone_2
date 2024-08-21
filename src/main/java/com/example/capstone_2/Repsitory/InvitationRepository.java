package com.example.capstone_2.Repsitory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.capstone_2.Model.Invitation;

@Repository
public interface InvitationRepository extends JpaRepository<Invitation, Integer>{
    Optional<Invitation> findInvitationByStatus(Invitation.Status status); 
    Optional<Invitation> findInvitationById(Integer id); 
}
