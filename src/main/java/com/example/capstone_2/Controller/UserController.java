package com.example.capstone_2.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.capstone_2.API.ApiResponse;
import com.example.capstone_2.Model.User;
import com.example.capstone_2.Service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService; 

    // ========================= GET ALL USERS =========================
    @GetMapping
    public ResponseEntity getUsers(){
        return ResponseEntity.ok(userService.getUsers()); 
    }


    // ========================= GET USER BY ID =========================
    @GetMapping("/{id}")
    public ResponseEntity getUserById(@PathVariable Integer userId){
        return ResponseEntity.ok(userService.getUser(userId)); 
    }


    // ========================= CREATE NEW USER =========================
    @PostMapping
    public ResponseEntity createUser(@Valid @RequestBody User user){
        userService.createUser(user);
        return ResponseEntity.ok(new ApiResponse("Create User successfully")); 
    }


    // ========================= UPDATE USER =========================
    @PutMapping
    public ResponseEntity updateUser(@RequestParam Integer id, @Valid @RequestBody User user){
        userService.updateUser(id, user);
        return ResponseEntity.ok(new ApiResponse("Update User successfully")); 
    }


    // ========================= DELETE USER =========================
    @DeleteMapping
    public ResponseEntity updateUser(@RequestParam Integer id){
        userService.deleteUser(id);
        return ResponseEntity.ok(new ApiResponse("Delete User successfully")); 
    }


    @PutMapping("/sub/{userId}")
    public ResponseEntity sub(@PathVariable Integer userId){
        userService.sub(userId);
        return ResponseEntity.ok(new ApiResponse("Subscribe Done")); 
    }

    @PutMapping("/{userId}/add-funds")
    public ResponseEntity addFunds(@PathVariable Integer userId, @RequestParam Double amount){
        userService.addFunds(userId, amount);
        return ResponseEntity.ok(new ApiResponse("Added "+ amount +" to wallet")); 
    }
}
