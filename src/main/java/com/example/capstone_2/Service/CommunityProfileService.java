package com.example.capstone_2.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.capstone_2.API.ApiException;
import com.example.capstone_2.Model.CommunityProfile;
import com.example.capstone_2.Model.Project;
import com.example.capstone_2.Model.User;
import com.example.capstone_2.Repsitory.CommunityProfileRepository;
import com.example.capstone_2.Repsitory.ProjectRepository;
import com.example.capstone_2.Repsitory.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommunityProfileService {

    private final CommunityProfileRepository communityProfileRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository; 

    // ========================= GET ALL COMMUNITY PROFILES =========================
    public List<CommunityProfile> getCommunityProfiles(){
        return communityProfileRepository.findAll(); 
    }


    // ========================= GET COMMUNITY PROFILE BY ID =========================
    public CommunityProfile getCommunityProfileByID(Integer id){
        CommunityProfile exploreCommunity = communityProfileRepository.findCommunityProfileById(id)
            .orElseThrow(() -> new ApiException("COMMUNITY  PROFILE NOT FOUND"));
        return exploreCommunity; 
    }


    // ========================= CREATE NEW COMMUNITY PROFLIE =========================
    public void createCommunityProfile(CommunityProfile communityProfile){
        User user = userRepository.findUserById(communityProfile.getUserId())
            .orElseThrow(() -> new ApiException("USER NOT FOUND"));

        communityProfile.setName(user.getUsername());
        communityProfileRepository.save(communityProfile); 
    }


    // ========================= UPDATE COMMUNITY PROFILE =========================
    public void updateCommunityProfile(Integer id, CommunityProfile updateCommunityProfile){
        CommunityProfile communityProfile = communityProfileRepository.findCommunityProfileById(id)
            .orElseThrow(() -> new ApiException("COMMMUNITY PROFILE NOT FOUND"));
        communityProfile.setName(updateCommunityProfile.getName());
        communityProfileRepository.save(communityProfile); 
    }


    // ========================= DELETE COMMUNITY PROFILE =========================
    public void deleteCommunityProfile(Integer id){
        CommunityProfile communityProfile = communityProfileRepository.findCommunityProfileById(id)
            .orElseThrow(() -> new ApiException("COMMMUNITY PROFILE NOT FOUND"));

        communityProfileRepository.delete(communityProfile);
    }


    // =================================================================
    public CommunityProfile getProfileByUserId(Integer userId){
        return communityProfileRepository.findCommunityProfileByUserId(userId)
            .orElseThrow(() -> new ApiException("PROFILE NOT FOUND")); 
    }

    public void addProjectToProfile(Integer userId, Project project){
        CommunityProfile profile = getProfileByUserId(userId); 
        project.setCommunityProfileId(profile.getId());
        project.setStatus(Project.Status.DRAFT);
        projectRepository.save(project); 
    }

    // ========================= FOLLOW =========================
    public void followUser(Integer profileId, Integer followerId){
        if(profileId.equals(followerId)){
            throw new ApiException("USER CANNOT FOLLOW THEMSELF"); 
        }
        CommunityProfile profile = communityProfileRepository.findCommunityProfileById(profileId)
            .orElseThrow(() -> new ApiException("PROFILE NOT FOUND")); 
            
        User user = userRepository.findUserById(followerId)
            .orElseThrow(() -> new ApiException("USER NOT FOUND")); 
        
        if(profile.getFollowers().add(followerId)){
            profile.setFollowersCount(profile.getFollowersCount() + 1);
            communityProfileRepository.save(profile); 
        }
    }


    // ========================= UNFOLLOW =========================
    public void unfollowUser(Integer profileId, Integer followerId){
        CommunityProfile profile = communityProfileRepository.findCommunityProfileById(profileId)
            .orElseThrow(() -> new ApiException("PROFILE NOT FOUND")); 

        if(!profile.getFollowers().remove(followerId)){
            throw new ApiException("USER NOT FOLLOWING THIS PROFILE"); 
        }

        profile.setFollowersCount(profile.getFollowersCount() - 1);
        communityProfileRepository.save(profile); 
    }
}
