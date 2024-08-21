package com.example.capstone_2.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.capstone_2.API.ApiException;
import com.example.capstone_2.Model.ExploreCommunity;
import com.example.capstone_2.Repsitory.ExploreCommunityRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ExploreCommunityService {

    private final ExploreCommunityRepository exploreCommunityRepository; 

    // ========================= GET ALL EXPLORE COMMUNITY =========================
    public List<ExploreCommunity> getExploreCommunity(){
        return exploreCommunityRepository.findAll(); 
    }


    // ========================= GET EXPLORE COMMUNITY BY ID =========================
    public ExploreCommunity getExploreCommunityByID(Integer id){
        ExploreCommunity exploreCommunity = exploreCommunityRepository.findExploreCommunityById(id)
            .orElseThrow(() -> new ApiException("EXPLORE COMMUNITY NOT FOUND")); 
        return exploreCommunity; 
    }


    // ========================= CREATE NEW EXPLORE COMMUNITY =========================
    public void createExploreCommunity(ExploreCommunity exploreCommunity){
        exploreCommunityRepository.save(exploreCommunity); 
    }


    // ========================= UPDATE EXPLORE COMMUNITY =========================
    public void updateExploreCommunity(Integer id, ExploreCommunity updateExploreCommunity){
        ExploreCommunity exploreCommunity = exploreCommunityRepository.findExploreCommunityById(id)
            .orElseThrow(() -> new ApiException("EXPLORE COMMMUNITY NOT FOUND"));
        exploreCommunity.setName(updateExploreCommunity.getName());
        exploreCommunityRepository.save(exploreCommunity); 
    }


    // ========================= DELETE EXPLORE COMMUNITY =========================
    public void deleteExploreCommunity(Integer id){
        ExploreCommunity exploreCommunity = exploreCommunityRepository.findExploreCommunityById(id)
            .orElseThrow(() -> new ApiException("EXPLORE COMMMUNITY NOT FOUND"));

        exploreCommunityRepository.delete(exploreCommunity);
    }

}
