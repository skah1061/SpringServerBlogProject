package com.sparta.blog.repository;

import com.sparta.blog.Entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Repository
public interface PostRepository  extends JpaRepository<Post, Long> {
//        List<Post> findAllByOrderBywriteDateDesc();
        List<Post> findAll();
}

