package com.example.capstone_2.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.capstone_2.API.ApiException;
import com.example.capstone_2.Model.Invitation;
import com.example.capstone_2.Model.Team;
import com.example.capstone_2.Model.User;
import com.example.capstone_2.Repsitory.InvitationRepository;
import com.example.capstone_2.Repsitory.TeamRepository;
import com.example.capstone_2.Repsitory.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InvitationService {

    private final InvitationRepository invitationRepository;
    private final UserRepository userRepository;
    private final TeamRepository teamRepository;
    private final TeamService teamService;  

    // ========================= GET ALL INVITATIONS =========================
    public List<Invitation> getInvitations(){
        return invitationRepository.findAll();
    }


    // ========================= GET INVITATION BY STATUS =========================
    public Invitation getInvitationByStatus(Invitation.Status status){
        Invitation invitation = invitationRepository.findInvitationByStatus(status)
            .orElseThrow(() -> new ApiException("NOT FOUND INVITATION"));
            
        return invitation;
    }


    // ========================= CREATE NEW INVITATION =========================
    public Invitation createInvitation(Integer teamId, Integer invitedUserId, Integer inviterUserId){
        Team team = teamRepository.findTeamById(teamId)
            .orElseThrow(() -> new ApiException("TEAM NOT FOUND")); 

        User invitedUser = userRepository.findUserById(invitedUserId)
            .orElseThrow(() -> new ApiException("INVITED USER NOT FOUND")); 

        User inviterUser = userRepository.findUserById(inviterUserId)
            .orElseThrow(() -> new ApiException("INVITER USER NOT FOUND")); 


        if (invitationRepository.findAll().stream().anyMatch(invitation -> 
            invitation.getTeamId().equals(teamId) &&
            invitation.getInvitedUserId().equals(inviterUserId))){
                throw new ApiException("INVITATION ALREADY EXISTS"); 
            }
        
        Invitation invitation = new Invitation(); 
        invitation.setTeamId(teamId);
        invitation.setInvitedUserId(invitedUserId);
        invitation.setInviterUserId(inviterUserId);
        invitation.setStatus(Invitation.Status.PENDING);
        return invitationRepository.save(invitation); 
    }

    // ========================= ACCEPT INVITATION =========================
    public void acceptInvitation(Integer invitationId){
        Invitation invitation = invitationRepository.findInvitationById(invitationId)
            .orElseThrow(() -> new ApiException("INVITATION NOT FOUND")); 

        if(invitation.getStatus().equals(Invitation.Status.PENDING)){
            invitation.setStatus(Invitation.Status.ACCEPTED);
            invitationRepository.save(invitation);
            teamService.addUserToTeam(invitation.getTeamId(), invitation.getInvitedUserId());
        }else {
            throw new ApiException("INVITATION CANNOT BE ACCEPTED"); 
        }
    }


    // ========================= REJECT INVITATION =========================
    public void rejectInvitation(Integer invitationId){
        Invitation invitation = invitationRepository.findInvitationById(invitationId)
            .orElseThrow(() -> new ApiException("INVITATION NOT FOUND")); 

        if(invitation.getStatus().equals(Invitation.Status.PENDING)){
            invitation.setStatus(Invitation.Status.REJECTED);
            invitationRepository.save(invitation); 
        }else {
            throw new ApiException("INVITATION CANNOT BE ACCEPTED"); 
        }
    }

        
    // ========================= UPDATE INVITATION =========================
    public void updateInvitation(Integer id, Invitation updateInvitation){
        Invitation invitation = invitationRepository.findInvitationById(id)
            .orElseThrow(() -> new ApiException("NOT FOUND INVITATION"));
        
        invitation.setInvitedUserId(updateInvitation.getInvitedUserId());
        invitation.setInviterUserId(updateInvitation.getInviterUserId());
        invitation.setStatus(updateInvitation.getStatus());
        invitationRepository.save(invitation); 
    }

    // ========================= DELETE INVITATION =========================
    public void deleteInvitation(Integer id){
        Invitation invitation = invitationRepository.findInvitationById(id)
            .orElseThrow(() -> new ApiException("NOT FOUND INVITATION"));
        
        invitationRepository.delete(invitation);
    }
}
