package com.example.capstone_2.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.capstone_2.API.ApiException;
import com.example.capstone_2.Model.CommunityProfile;
import com.example.capstone_2.Model.Project;
import com.example.capstone_2.Model.Team;
import com.example.capstone_2.Model.User;
import com.example.capstone_2.Repsitory.CommunityProfileRepository;
import com.example.capstone_2.Repsitory.ProjectRepository;
import com.example.capstone_2.Repsitory.TeamRepository;
import com.example.capstone_2.Repsitory.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository; 
    private final TeamRepository teamRepository; 
    private final UserRepository userRepository; 
    private final CommunityProfileRepository communityProfileRepository; 

    // ========================= GET ALL PROJECTS =========================
    public List<Project> getProjects(){
        return projectRepository.findAll(); 
    }

    // ========================= GET PROJECT BY ID =========================
    public Project getProjectById(Integer id){
        return projectRepository.findProjectById(id)
            .orElseThrow(() -> new ApiException("PROJECT NOT FOUND"));  
    }


    // ========================= CREATE NEW PROJECT =========================
    public void createProject(Project project, Integer userId){
        User user = userRepository.findUserById(userId)
            .orElseThrow(() -> new ApiException("USER NOT FOUND"));
        
        project.setUserId(userId);
        projectRepository.save(project); 
    }


    // ========================= UPDATE PROJECT =========================
    public void updateProject(Integer id, Project updateProject){
        Project project = projectRepository.findProjectById(id)
            .orElseThrow(() -> new ApiException("PROJECT NOT FOUND")); 
        project.setName(updateProject.getName());
        project.setStatus(updateProject.getStatus());
        projectRepository.save(project); 
    }


    // ========================= DELETE PROJECT =========================
    public void deleteProject(Integer id){
        Project project = projectRepository.findProjectById(id)
            .orElseThrow(() -> new ApiException("PROJECT NOT FOUND")); 
        projectRepository.delete(project);
    }


    // ========================= PUBLISH PROJECT TO TEAM =========================
    public void publishProjectToTeam(Integer projectId, Integer teamId){
        Project project = getProjectById(projectId); 
        Team team = teamRepository.findTeamById(teamId)
            .orElseThrow(() -> new ApiException("TEAM NOT FOUND")); 

        project.setStatus(Project.Status.PUBLISHED);
        project.setTeamId(teamId);
        projectRepository.save(project); 
    }


    // ========================= PUBLISH PROJECT TO COMMUNITY PROFILE ID =========================
    public void publishProjectToCommunityProfile(Integer projectId, Integer userId, double price){
        Project project = getProjectById(projectId); 
        CommunityProfile profile = communityProfileRepository.findCommunityProfileByUserId(userId)
            .orElseThrow(() -> new ApiException("PROFILE NOT FOUND")); 

        if(project.getCommunityProfileId() != null){
            throw new ApiException("PROJECT ALREADY PUBLISHED"); 
        }

        project.setStatus(Project.Status.PUBLISHED);
        project.setPrice(price);
        project.setCommunityProfileId(profile.getId());
        projectRepository.save(project); 
    }


    // ========================= GET PROJECTS BY TEAM ID =========================
    public List<Project> getProjectsByTeamId(Integer id){
        Team teamId = teamRepository.findTeamById(id)
            .orElseThrow(() -> new ApiException("TEAM NOT FOUND"));

        List<Project> projects = projectRepository.findProjectsByTeamId(id)
            .orElseThrow(() -> new ApiException("Not found any projects for this team"));
            
        return projects; 
    }

    // ========================= GET PROJECTS BY PROFILE ID =========================
    public List<Project> getProjectsByProfileId(Integer id){
        CommunityProfile profile = communityProfileRepository.findCommunityProfileById(id)
            .orElseThrow(() -> new ApiException("PROFILE NOT FOUND")); 

        List<Project> projects = projectRepository.findProjectByCommunityProfileId(id)
            .orElseThrow(() -> new ApiException("Not found any projects for this team"));
            
        return projects; 
    }

    // ========================= GET ALL PUBLISHED PROJECTS =========================
    public List<Project> getAllPublishedProjects(){
        return projectRepository.findProjectByStatus(Project.Status.PUBLISHED); 
    }

    
    // ========================= PURCHASE PROJECT =========================
    public void purchaseProject(Integer projectId, Integer userId){
        Project project = projectRepository.findProjectById(projectId)
            .orElseThrow(() -> new ApiException("PROJECT NOT FOUND")); 

        User user = userRepository.findUserById(userId)
            .orElseThrow(() -> new ApiException("USER NOT FOUND")); 

        if(project.getCommunityProfileId() == null){
            throw new ApiException("PROJECT NOT AVAILABLE FOR PURCHASE"); 
        }

        if(project.getUserId().equals(userId)){
            throw new ApiException("USER CANNOT PURCHASE OWN PROJECT"); 
        }

        if(!project.getStatus().equals(Project.Status.PUBLISHED)){
            throw new ApiException("PROJECT IS NOT PUBLISHED"); 
        }

        if(user.getBalance() < project.getPrice()){
            throw new ApiException("INSUFFICIENT BALANCE"); 
        }

        user.setBalance(user.getBalance() - project.getPrice());
        user.getPurchaseProjectIds().add(projectId); 
        userRepository.save(user); 
        
        // project.setStatus(Project.Status.PURCHASED);
        projectRepository.save(project);
        
        CommunityProfile profile = communityProfileRepository.findCommunityProfileById(project.getCommunityProfileId())
            .orElseThrow(() -> new ApiException("PROFILE NOT FOUND")); 

        profile.setPurchasesCount(profile.getPurchasesCount() + 1);
        profile.setTotalRevenue(profile.getTotalRevenue() + project.getPrice());
        communityProfileRepository.save(profile); 
    }


    // ========================= ADD COMMENT =========================
    public void addCommentToProject(Integer projectId, String comment){
        Project project = projectRepository.findProjectById(projectId)
            .orElseThrow(() -> new ApiException("PROJECT NOT FOUND")); 
        
        if(project.getCommunityProfileId() == null){
            throw new ApiException("COMMENTS CAN ONLY BE ADDED TO PUBLISHED PROJECTS IN COMMUNITY PROFILE"); 
        }

        project.getComments().add(comment); 
        projectRepository.save(project); 
    }

    // ========================= ADD RATING =========================
    public void addRatingToProject(Integer projectId, Integer rating){
        Project project = projectRepository.findProjectById(projectId)
            .orElseThrow(() -> new ApiException("PROJECT NOT FOUND")); 
        
        CommunityProfile profile = communityProfileRepository.findCommunityProfileById(project.getUserId())
            .orElseThrow(() -> new ApiException("PROFILE NOT FOUND")); 

        if(project.getCommunityProfileId() == null){
            throw new ApiException("RATING CAN ONLY BE ADDED TO PUBLISHED PROJECTS IN COMMUNITY PROFILE"); 
        }

        if(rating < 1 || rating > 5){
            throw new ApiException("RATING MUST BE BETWEEN 1 TO 5"); 
        }

        project.getRatings().add(rating); 
        project.setAverageRating(project.calcAverageRating());
        projectRepository.save(project); 
    }


    // ========================= ADD LIKE =========================
    public void addLikeToProject(Integer projectId){
        Project project = projectRepository.findProjectById(projectId)
            .orElseThrow(() -> new ApiException("PROJECT NOT FOUND")); 
        
        if(project.getCommunityProfileId() == null){
            throw new ApiException("LIKES CAN ONLY BE ADDED TO PUBLISHED PROJECTS IN COMMUNITY PROFILE"); 
        }

        project.setLikes(project.getLikes() + 1); 
        projectRepository.save(project); 
    }



}
