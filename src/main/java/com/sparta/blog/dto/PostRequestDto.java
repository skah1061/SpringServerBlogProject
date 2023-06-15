package com.sparta.blog.dto;

import lombok.Getter;

@Getter
public class PostRequestDto {
    String title;
    String contents;
    String writerName;
    String password;
}
