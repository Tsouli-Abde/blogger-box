package com.dauphine.blogger.controllers;

import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@Tag(
    name = "HelloWorld API",
    description = "here is my endpoint"
)
public class HelloWorld {
   
    @GetMapping("hello-world")
    @Operation(
        summary = "this one returns a hello world",
        description = "this is the description"
    )
    public String HelloWorld(){
        return "Hello World";
    }

    @GetMapping("hello/{name}")
    public String HelloByName(
        @Parameter(description = "Name to greet")
        @PathVariable String name) {
        return "Hello " + name;
    }

    @GetMapping("hello/v2")
    public String HelloByNameV2(@RequestParam String name) {
        return "Hello" + name;
    }
}
