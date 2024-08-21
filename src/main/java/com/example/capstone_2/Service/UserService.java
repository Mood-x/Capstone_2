package com.example.capstone_2.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.capstone_2.API.ApiException;
import com.example.capstone_2.Model.Plan;
import com.example.capstone_2.Model.Plan.PlanType;
import com.example.capstone_2.Model.User;
import com.example.capstone_2.Repsitory.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository; 

    // ========================= GET ALL USERS =========================
    public List<User> getUsers(){ 
        return userRepository.findAll(); 
    }


    // ========================= GET USER BY ID =========================
    public User getUser(Integer id){
        User user = userRepository.findUserById(id)
            .orElseThrow(() -> new ApiException("USER NOT FOUND"));
        return user; 
    }


    // ========================= CREATE NEW USER =========================
    public void createUser(User user){
        userRepository.save(user); 
    }


    // ========================= UPDATE USER =========================
    public void updateUser(Integer id, User updateUser){
        User user = userRepository.findUserById(id)
            .orElseThrow(() -> new ApiException("USER NOT FOUND"));
        user.setUsername(updateUser.getUsername());
        user.setEmail(updateUser.getEmail());
    }


    // ========================= DELETE USER =========================
    public void deleteUser(Integer id){
        User user = userRepository.findUserById(id)
            .orElseThrow(() -> new ApiException("USER NOT FOUND"));
        userRepository.delete(user);
    }



    // ========================= SUBSCRIBE =========================
    public void sub(Integer userId){
        User user = userRepository.findUserById(userId)
            .orElseThrow(() -> new ApiException("USER NOT FOUND"));

        if(user.getPlan().equals(Plan.PlanType.PROFESSIONAL)){
            throw new ApiException("USER ALREADY SUBSCRIBE"); 
        }
        
        user.setBalance(user.getBalance() - 50);
        user.setPlan(PlanType.PROFESSIONAL);
        userRepository.save(user);
    }

    // ========================= ADD FUNDS TO WALLET =========================
    public void addFunds(Integer userId, Double amount) {
        User user = userRepository.findUserById(userId)
            .orElseThrow(() -> new ApiException("USER NOT FOUND"));
        if (amount <= 0) {
            throw new ApiException("AMOUNT MUST BE POSITIVE");
        }

        user.setBalance(user.getBalance() + amount);
        userRepository.save(user); 
    }
}
