package com.example.PresProProject.controllers;

import com.example.PresProProject.ApiResponse;
import com.example.PresProProject.UserLocation;
import com.example.PresProProject.entities.Provider;
import com.example.PresProProject.services.IProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/providers")
public class ProviderController {
    @Autowired
    IProviderService providerService;
    ApiResponse apiResponse = new ApiResponse();

    @PostMapping("/save")
    public ResponseEntity<?>save(@RequestBody Provider provider){
        apiResponse.setResponseCode("00");
        apiResponse.setResponseMessage("Provider Created");
        apiResponse.setData(providerService.save(provider));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @GetMapping("/closest")
    public ResponseEntity<?>findClosest(@RequestBody UserLocation userLocation){
       Collection<Provider>providers = providerService.findClosest(userLocation);
       apiResponse.setData(providers);
       return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<?>findAll(@RequestParam int page,@RequestParam int size){

        Page<Provider>providers =providerService.findAll(page,size);
        apiResponse.setData(providers);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
}
