package com.sparta.blog.dto;

import com.sparta.blog.Entity.Post;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PostDetailDto {
    String title;       //제목
    String contents;    //내용
    LocalDateTime createAt;     //작성시간
    String writerName;  //작성자

    public PostDetailDto(Post post){
        this.title = post.getTitle();
        this.contents = post.getContents();
        this.createAt = post.getCreateAt();
        this.writerName = post.getWriterName();
    }
}
