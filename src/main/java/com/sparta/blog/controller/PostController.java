package com.sparta.blog.controller;

import com.sparta.blog.dto.PostDetailDto;
import com.sparta.blog.dto.PostRequestDto;
import com.sparta.blog.dto.PostResponseDto;
import com.sparta.blog.dto.UserRequestDto;
import com.sparta.blog.service.PostService;
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
    public PostResponseDto createBlog(@RequestBody PostRequestDto requestDto) {
        return postService.createBlog(requestDto);
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
    public PostDetailDto updateBlog(@PathVariable Long id, PostDetailDto postDetailDto
            , @RequestBody Map<String,String> password) {
        return postService.updatePost(id,postDetailDto,password.get("password"));
    }

    @DeleteMapping("/blog/{id}")
    public String deletePost(@PathVariable Long id, @RequestBody Map<String,String> password) {
        return postService.deletePost(id,password.get("password"));
    }

}
