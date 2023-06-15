package com.sparta.blog.service;

import com.sparta.blog.Entity.Post;
import com.sparta.blog.dto.PostRequestDto;
import com.sparta.blog.dto.PostResponseDto;
import com.sparta.blog.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    @Autowired
    public PostService(PostRepository postRepository){
        this.postRepository = postRepository;
    }
    public PostResponseDto createBlog(PostRequestDto requestDto) {

        //엔티티로 변환해주는 단계
        Post post = new Post(requestDto);

        //id체크
        Post savePost = postRepository.save(post);

        //Entity를 ResponseDto로
        PostResponseDto postResponseDto = new PostResponseDto(post);

       return postResponseDto;
    }

    public List<PostResponseDto> getPost() {
        //DB조회하기
        return postRepository.findAll().stream().map(PostResponseDto::new).toList();
    }
    @Transactional
    public Long updatePost(Long id, PostRequestDto requestDto){
        Post post = findPost(id);
        post.update(requestDto);

        return id;
    }
    public Long deletePost(Long id){
        Post post = findPost(id);

        postRepository.delete(post);
        return id;
    }

    private Post findPost(Long id){
        Post post = postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 게시글이 존재하지 않습니다.")

        );
        return post;
    }
}
