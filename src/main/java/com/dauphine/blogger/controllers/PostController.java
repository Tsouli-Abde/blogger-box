package com.dauphine.blogger.controllers;

import com.dauphine.blogger.dtos.PostRequest;
import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.services.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v1/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> getAll(@RequestParam(required = false) String value) {
        if (value != null && !value.isBlank()) {
            return postService.search(value);
        }
        return postService.getAll();
    }

    @GetMapping("{id}")
    public Post getById(@PathVariable UUID id) {
        return postService.getById(id);
    }

    @PostMapping
    public ResponseEntity<Post> create(@RequestBody PostRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                postService.create(req));
    }

    @PutMapping("{id}")
    public Post update(@PathVariable UUID id, @RequestBody PostRequest req) {
        return postService.update(id, req);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        postService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
