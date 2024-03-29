package com.example.PresProProject.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/")
@CrossOrigin(origins = "*")
public class HomeController {

    @GetMapping("")
    public ResponseEntity<?> home(){

        return ResponseEntity.status(302).header("","welcome").body("WELCOME TO PRESPRO PROJECT");
    }
}
