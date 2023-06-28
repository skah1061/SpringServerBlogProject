package com.sparta.blog.controller;

import com.sparta.blog.dto.PostDetailDto;
import com.sparta.blog.dto.PostRequestDto;
import com.sparta.blog.dto.PostResponseDto;
import com.sparta.blog.dto.UserRequestDto;
import com.sparta.blog.jwt.JwtUtil;
import com.sparta.blog.security.UserDetailsImpl;
import com.sparta.blog.service.PostService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PostController {
    private final PostService postService;
    public PostController(PostService postService){
        this.postService = postService;
    }

    @PostMapping("/blog")
    public PostResponseDto createBlog(HttpServletRequest httpServletRequest, @RequestBody PostRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.createBlog(httpServletRequest,requestDto, userDetails);
    }
    @GetMapping("/blog/detail/{id}")
    public PostDetailDto detailInquiry(@PathVariable Long id){
        return postService.detailPost(id);
    }
    @GetMapping("/blog")
//    @ResponseBody
    public List<PostDetailDto> getBlog() {
        return postService.getPost();
    }

    @PutMapping("/blog/{id}")
    public PostDetailDto updateBlog(HttpServletRequest req,@PathVariable Long id,@RequestBody PostDetailDto postDetailDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.updatePost(req,id,postDetailDto, userDetails);
    }

    @DeleteMapping("/blog/{id}")
    public String deletePost(HttpServletRequest req,@PathVariable Long id, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return postService.deletePost(req,id,userDetails);
    }

}
