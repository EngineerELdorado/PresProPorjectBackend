package com.example.PresProProject.controllers;

import com.example.PresProProject.ApiResponse;
import com.example.PresProProject.entities.Category;
import com.example.PresProProject.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    ApiResponse apiResponse = new ApiResponse();

    @Autowired
    CategoryRepository categoryRepository;
    @PostMapping("/save")
    public ResponseEntity<?>save(@RequestBody Category category){
      apiResponse.setResponseCode("00");
      apiResponse.setResponseMessage("Category saved");
      apiResponse.setData(categoryRepository.save(category));
      return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    @GetMapping("/all")
    public ResponseEntity<?>findAll(){
        apiResponse.setData(categoryRepository.findAll());
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
}
