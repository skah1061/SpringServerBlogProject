package com.sparta.blog.service;

import com.sparta.blog.Entity.Post;
import com.sparta.blog.dto.PostDetailDto;
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

    public List<PostDetailDto> getPost() {
        //DB조회하기
        return postRepository.findAllByOrderByCreateAtDesc().stream().map(PostDetailDto::new).toList();
    }
    @Transactional
    public PostRequestDto updatePost(Long id, PostRequestDto requestDto, String password){

        Post post = findPost(id);
        if(password.equals(post.getPassword())) {
            post.update(requestDto);

            return requestDto;
        }
        else{
            throw new IllegalArgumentException("암호가 틀립니다.");
        }
    }
    public String deletePost(Long id,String password){

        Post post = findPost(id);
        if(password.equals(post.getPassword())) {
            postRepository.delete(post);
            return "삭제완료";
        }
        else{
            return "암호가 틀렸습니다.";
        }
    }
    public PostDetailDto detailInquiry(Long id) {
        Post post = findPost(id);
        PostDetailDto postDetailDto = new PostDetailDto(post);
        return postDetailDto;
    }
    private Post findPost(Long id){
        Post post = postRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 게시글이 존재하지 않습니다.")

        );
        return post;
    }


}
