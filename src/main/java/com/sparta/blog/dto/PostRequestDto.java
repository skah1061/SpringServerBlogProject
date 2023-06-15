package com.sparta.blog.dto;

import lombok.Getter;

@Getter
public class PostRequestDto {
    String title;   //제목
    String contents;    //내용
    String writerName;  //작성자명
    String password;    //비밀번호
}
