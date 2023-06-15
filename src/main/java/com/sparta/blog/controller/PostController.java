package com.sparta.blog.controller;

import com.sparta.blog.Entity.Post;
import com.sparta.blog.dto.PostRequestDto;
import com.sparta.blog.dto.PostResponseDto;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class PostController {
    private Map<Long, Post> postList = new HashMap<>();
    @PostMapping("/blog")
    public PostResponseDto postBlog(@RequestBody PostRequestDto requestDto)
    {
        //엔티티로 변환해주는 단계
        Post post = new Post(requestDto);

        //id체크
        Long maxId = postList.size()>0? Collections.max(postList.keySet())+1:1;
        post.setId(maxId);

        postList.put(post.getId(),post);
        PostResponseDto postResponseDto = new PostResponseDto(post);
        System.out.printf("등록완료!!!");
        return postResponseDto;
    }

    @GetMapping("/blog")
//    @ResponseBody
    public List<PostResponseDto> getBlog(){
        //mep리스트로 변환
        List<PostResponseDto> responseList = postList.values().stream()
                .map(PostResponseDto::new).toList();
        System.out.printf("Get완료");

        return responseList;
    }
}
