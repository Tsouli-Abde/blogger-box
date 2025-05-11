package com.dauphine.blogger.repositories;

import java.util.UUID;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.dauphine.blogger.models.Post;

public interface PostRepository extends JpaRepository<Post, UUID> {
    List<Post> findByTitleContainingIgnoreCase(String title);
}
