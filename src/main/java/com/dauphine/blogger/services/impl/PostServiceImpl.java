package com.dauphine.blogger.services.impl;

import com.dauphine.blogger.dtos.PostRequest;
import com.dauphine.blogger.exceptions.ResourceNotFoundException;
import com.dauphine.blogger.models.Category;
import com.dauphine.blogger.models.Post;
import com.dauphine.blogger.repositories.CategoryRepository;
import com.dauphine.blogger.repositories.PostRepository;
import com.dauphine.blogger.services.PostService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final CategoryRepository categoryRepository;

    public PostServiceImpl(PostRepository postRepository, CategoryRepository categoryRepository) {
        this.postRepository = postRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Post> getAll() {
        return postRepository.findAll();
    }

    @Override
    public Post getById(UUID id) {
        return postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));
    }

    @Override
    public Post create(PostRequest req) {
        Category category = categoryRepository.findById(req.getCategoryId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Category with id " + req.getCategoryId() + " not found"));

        Post post = new Post(
                UUID.randomUUID(),
                req.getTitle(),
                req.getContent(),
                LocalDateTime.now(),
                category);

        return postRepository.save(post);
    }

    @Override
    public Post update(UUID id, PostRequest req) {
        Post existing = getById(id);
        Category category = categoryRepository.findById(req.getCategoryId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Category with id " + req.getCategoryId() + " not found"));

        existing.setTitle(req.getTitle());
        existing.setContent(req.getContent());
        existing.setCreatedDate(req.getCreatedDate());
        existing.setCategory(category);

        return postRepository.save(existing);
    }

    @Override
    public boolean deleteById(UUID id) {
        postRepository.deleteById(id);
        return true;
    }

    @Override
    public List<Post> search(String value) {
        return postRepository.findByTitleContainingIgnoreCase(value);
    }
}
