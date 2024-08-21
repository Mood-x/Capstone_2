package com.example.capstone_2.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.capstone_2.API.ApiResponse;
import com.example.capstone_2.Model.Invitation;
import com.example.capstone_2.Service.InvitationService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/invitations")
@RequiredArgsConstructor
public class InvitationController {
    
    private final InvitationService invitationService; 

    // ========================= GET ALL INVITATIONS =========================
    @GetMapping
    public ResponseEntity getInvitations(){
        return ResponseEntity.ok(invitationService.getInvitations());  
    }

    // ========================= GET INVITATIONS BY STATUS =========================
    @GetMapping("/status")
    public ResponseEntity getInvitationByStatus(@RequestParam Invitation.Status status){
        return ResponseEntity.ok(invitationService.getInvitationByStatus(status)); 
    }


    // ========================= CREATE NEW INVITATION =========================
    @PostMapping("/{teamId}/{invitedUserId}/{inviterUserId}")
    public ResponseEntity createInvitation(@PathVariable Integer teamId, @PathVariable Integer invitedUserId, @PathVariable Integer inviterUserId){
        invitationService.createInvitation(teamId, invitedUserId, inviterUserId); 
        return ResponseEntity.ok(new ApiResponse("Send Invitation")); 
    }

    
    // ========================= UPDATE INVITATION =========================
    @PutMapping
    public ResponseEntity updateInvitation(@PathVariable Integer id, Invitation updateInvitation){
        invitationService.updateInvitation(id, updateInvitation);
        return ResponseEntity.ok(new ApiResponse("Update Invitation")); 
    }
    

    // ========================= DELETE INVITATION =========================
    @DeleteMapping
    public ResponseEntity deleteInvitation(@PathVariable Integer id){
        invitationService.deleteInvitation(id);
        return ResponseEntity.ok(new ApiResponse("Delete Invitation")); 
    }
    
    // ========================= ACCEPT INVITATION =========================
    @PutMapping("/accept/{invitationId}")
    public ResponseEntity acceptInvitation(@PathVariable Integer invitationId){
        invitationService.acceptInvitation(invitationId);
        return ResponseEntity.ok(new ApiResponse("Accpted")); 
    }
    
    // ========================= REJECT INVITATION =========================
    @PutMapping("/reject/{invitationId}")
    public ResponseEntity rejectInvitation(@PathVariable Integer invitationId){
        invitationService.rejectInvitation(invitationId);
        return ResponseEntity.ok(new ApiResponse("Rejected")); 
    }
}
