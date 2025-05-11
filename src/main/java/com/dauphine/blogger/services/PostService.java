package com.dauphine.blogger.services;

import com.dauphine.blogger.dtos.PostRequest;
import com.dauphine.blogger.models.Post;
import java.util.List;
import java.util.UUID;

public interface PostService {
    List<Post> getAll();
    Post getById(UUID id);
    Post create(PostRequest req);
    Post update(UUID id, PostRequest req);
    boolean deleteById(UUID id);
    List<Post> search(String value);
}
