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
    public PostResponseDto postBlog(@RequestBody PostRequestDto requestDto) {
        //엔티티로 변환해주는 단계
        Post post = new Post(requestDto);

        //id체크
        Long maxId = postList.size() > 0 ? Collections.max(postList.keySet()) + 1 : 1;
        post.setId(maxId);

        postList.put(post.getId(), post);
        PostResponseDto postResponseDto = new PostResponseDto(post);
        System.out.printf("등록완료!!!");
        return postResponseDto;
    }

    @GetMapping("/blog")
//    @ResponseBody
    public List<PostResponseDto> getBlog() {
        //mep리스트로 변환
        List<PostResponseDto> responseList = postList.values().stream()
                .map(PostResponseDto::new).toList();
        System.out.printf("Get완현");

        return responseList;
    }

    @PutMapping("/blog/{id}")
    public Long updateBlog(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        //해당 포스트가 DB에 존재하는지 확인
        if (postList.containsKey(id)) {
            //해당하는 게시글 가져오기
            Post post = postList.get(id);

            post.update(requestDto, requestDto.getPassword());//임시 비밀번호
            return post.getId();
        } else {
            //게시글이 없을경우 예외처리
            throw new IllegalArgumentException("선택한 게시글은 존재하지 않습니다.");
        }
    }

    @DeleteMapping("/blog/{id}")
    public Long deletePost(@PathVariable Long id) {
        if (postList.containsKey(id)) {
            //해당하는 게시글 가져오기
            postList.remove(id);
            return id;
        } else {
            //게시글이 없을경우 예외처리
            throw new IllegalArgumentException("선택한 게시글은 존재하지 않습니다.");
        }
    }
}
