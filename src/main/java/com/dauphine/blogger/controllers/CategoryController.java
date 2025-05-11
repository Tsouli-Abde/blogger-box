package com.dauphine.blogger.controllers;

import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.services.CategoryService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("v1/categories")
public class CategoryController {
    
    private final List<Category> categories = new ArrayList<>();
    private final CategoryService categoryServiceImpl;
    
    public CategoryController(CategoryService categoryServiceImpl){
        this.categoryServiceImpl = categoryServiceImpl;
    }
    
    @GetMapping
    public List<Category> getAll() {
        return categoryServiceImpl.getAll();
    }

    @GetMapping("{id}")
    public Category getById(@PathVariable UUID id) {
        return categoryServiceImpl.getById(id);
    }
/* 
    @PostMapping
    public Category create(@RequestBody CategoryRequest categoryRequest) {
        categoryServiceImpl.create(null);
    }

    @PutMapping("{id}")
    public Category update(@PathVariable UUID id, @RequestBody CategoryRequest categoryRequest) {
        //TODO: process PUT request
        categoryServiceImpl.u;
    } */
}