package com.dauphine.blogger.controllers;

import com.dauphine.blogger.dtos.PostRequest;
import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.services.PostService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("v2/posts")
@Tag(name = "post-controller v2", description = "Improved version")
@Validated
public class PostControllerV2 {

    private final PostService postService;

    public PostControllerV2(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAll(@RequestParam(required = false) String value) {
        List<Post> posts = (value != null && !value.isBlank())
                ? postService.search(value)
                : postService.getAll();
        return ResponseEntity.ok(posts);
    }

    @GetMapping("{id}")
    public ResponseEntity<Post> getById(@PathVariable UUID id) {
        Post post = postService.getById(id);
        return ResponseEntity.ok(post);
    }

    @PostMapping
    public ResponseEntity<Post> create(@Valid @RequestBody PostRequest req) {
        Post createdPost = postService.create(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }

    @PutMapping("{id}")
    public ResponseEntity<Post> update(@PathVariable UUID id, @Valid @RequestBody PostRequest req) {
        Post updatedPost = postService.update(id, req);
        return ResponseEntity.ok(updatedPost);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        postService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
