package com.example.PresProProject.controllers;

import com.example.PresProProject.ApiResponse;
import com.example.PresProProject.entities.Admin;
import com.example.PresProProject.repositories.AdminRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Entity;

@RestController
@RequestMapping("/admins")
public class AdminController {

    @Autowired
    AdminRepository adminRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;
    ApiResponse apiResponse = new ApiResponse();
    @PostMapping("/save")
    public ResponseEntity<?>save(@RequestBody Admin admin){
        if (adminRepository.findByUsername(admin.getUsername())==null){
            admin.setPassword(bCryptPasswordEncoder.encode(admin.getPassword()));
            apiResponse.setResponseCode("00");
            apiResponse.setResponseMessage("Admin added");
            apiResponse.setData(adminRepository.save(admin));
        }else{
            apiResponse.setResponseCode("01");
            apiResponse.setResponseMessage("Username already taken");
        }

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?>findAll(int page, int size){
        Pageable pageable = PageRequest.of(page,size);
        apiResponse.setData(adminRepository.findAll(pageable));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestBody Admin admin){
        Admin found = adminRepository.findByUsername(admin.getUsername());
        if (found==null){
            apiResponse.setResponseCode("01");
            apiResponse.setResponseMessage("compte non existant");
        }else{
            if (bCryptPasswordEncoder.matches(admin.getPassword(), found.getPassword())){
                apiResponse.setResponseCode("00");
                apiResponse.setResponseMessage("Login Successful");
                apiResponse.setData(found);
            }else{
                apiResponse.setResponseCode("01");
                apiResponse.setResponseMessage("Login Failed");
            }
        }
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
}
