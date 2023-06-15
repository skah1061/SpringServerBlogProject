package com.sparta.blog.controller;

import com.sparta.blog.Entity.Post;
import com.sparta.blog.dto.PostDetailDto;
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
    @GetMapping("/blog/detail/{id}")
    public PostDetailDto detailInquiry(@PathVariable Long id){
        return postService.detailInquiry(id);
    }
    @GetMapping("/blog")
//    @ResponseBody
    public List<PostDetailDto> getBlog() {
        return postService.getPost();
    }

    @PutMapping("/blog/{id}")
    public PostRequestDto updateBlog(@PathVariable Long id, @RequestBody PostRequestDto requestDto
            , @RequestParam String password) {
        return postService.updatePost(id,requestDto,password);
    }

    @DeleteMapping("/blog/{id}")
    public String deletePost(@PathVariable Long id, @RequestParam String password) {
        return postService.deletePost(id,password);
    }
}
