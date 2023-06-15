package com.sparta.blog.dto;

import com.sparta.blog.Entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostResponseDto {
    Long id;    //id
    String title;   //제목
    String writerName;//작성자명
    String contents;//작성내용
    LocalDateTime writeDate;//작성 날짜
    String password;//비밀번호

    public PostResponseDto(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.writerName = post.getWriterName();
        this.contents = post.getContents();
        this.password = post.getPassword();
        //시간추가
    }
}
