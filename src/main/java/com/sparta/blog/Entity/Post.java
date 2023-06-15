package com.sparta.blog.Entity;

import com.sparta.blog.dto.PostRequestDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class Post {
    String title;   //제목
    String writerName;//작성자명
    String contents;//작성내용
    String password;//비밀번호

    Long id;    //id
    LocalDateTime writeDate;//작성 날짜


    public Post(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.writerName = requestDto.getWriterName();
        this.contents = requestDto.getContents();
        this.password = requestDto.getPassword();
    }

    public void update(PostRequestDto requestDto, String password) {
        if(requestDto.getPassword().equals(this.password)) {
            this.title = requestDto.getTitle();
            this.writerName = requestDto.getWriterName();
            this.contents = requestDto.getContents();
        }
        else{
            throw new IllegalArgumentException("해당 번호가 맞지 않습니다.");
        }
    }
}
