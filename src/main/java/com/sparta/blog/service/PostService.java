package com.sparta.blog.service;

import com.sparta.blog.Entity.Post;
import com.sparta.blog.dto.PostDetailDto;
import com.sparta.blog.dto.PostRequestDto;
import com.sparta.blog.dto.PostResponseDto;
import com.sparta.blog.jwt.JwtUtil;
import com.sparta.blog.repository.PostRepository;
import com.sparta.blog.security.UserDetailsImpl;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final JwtUtil jwtUtil;
    @Autowired
    public PostService(PostRepository postRepository, JwtUtil jwtUtil){
        this.postRepository = postRepository;
        this.jwtUtil = jwtUtil;
    }
    public PostResponseDto createBlog(HttpServletRequest req, PostRequestDto requestDto, UserDetailsImpl userDetails) {
        String token = jwtUtil.getTokenFromRequest(req);

        if(StringUtils.hasText(token)) {
            token = jwtUtil.substringToken(token);
            if (jwtUtil.validateToken(token)) {
                Post post = new Post(requestDto, userDetails.getUsername(), userDetails.getUser().getId());

                //id체크
                Post savePost = postRepository.save(post);

                //Entity를 ResponseDto로
                PostResponseDto postResponseDto = new PostResponseDto(post);

                return postResponseDto;
            }
        }
        //엔티티로 변환해주는 단계
        return null;
    }

    public List<PostDetailDto> getPost() {
        //DB조회하기
        return postRepository.findAllByOrderByCreateAtDesc().stream().map(PostDetailDto::new).toList();
    }
    @Transactional
    public PostDetailDto updatePost(HttpServletRequest req, Long id, PostDetailDto detailDto, UserDetailsImpl userDetails){
        String username = userDetails.getUsername();

        Post post = findPost(id);
        if(isToken(req)) {
            if (post.getWriterName().equals(username)) {
                detailDto.setWriterName(username);
                post.update(detailDto);

                return detailDto;
            } else {
                throw new IllegalArgumentException("수정할 권한이 없습니다.");
            }
        }
        else{
            throw new IllegalArgumentException("Token Error");
        }
    }
    public String deletePost(HttpServletRequest req,Long id,UserDetailsImpl userDetails){

        Post post = findPost(id);
        if(isToken(req)){
            if(post.getWriterName().equals(userDetails.getUsername())) {
                postRepository.delete(post);

                return "삭제완료";
            }
            else {
                throw new IllegalArgumentException("해당 권한이 없습니다.");
            }
        }
        else{
            throw new IllegalArgumentException("Token Error");
        }
    }
    public PostDetailDto detailPost(Long id) {
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
    private boolean isToken(HttpServletRequest req){
        String token = jwtUtil.getTokenFromRequest(req);
        if(StringUtils.hasText(token)){
            token =jwtUtil.substringToken(token);
            if(jwtUtil.validateToken(token)){
                return true;
            }

        }
        return false;
    }

}
