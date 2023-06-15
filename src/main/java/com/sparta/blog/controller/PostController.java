package com.sparta.blog.controller;

import com.sparta.blog.dto.PostRequestDto;
import com.sparta.blog.dto.PostResponseDto;
import com.sparta.blog.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {
    private final PostService postService;
    public PostController(PostService postService){
        this.postService = postService;
    }

    @PostMapping("/blog")
    public PostResponseDto createBlog(@RequestBody PostRequestDto requestDto) {
        return postService.createBlog(requestDto);
    }

    @GetMapping("/blog")
//    @ResponseBody
    public List<PostResponseDto> getBlog() {
        return postService.getPost();
    }

    @PutMapping("/blog/{id}")
    public Long updateBlog(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        return postService.updatePost(id,requestDto);
    }

    @DeleteMapping("/blog/{id}")
    public Long deletePost(@PathVariable Long id) {
        return postService.deletePost(id);
    }
}
