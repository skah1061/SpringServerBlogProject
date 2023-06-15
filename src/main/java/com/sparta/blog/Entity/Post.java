package com.sparta.blog.Entity;

import com.sparta.blog.dto.PostRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="blog")
@NoArgsConstructor
public class Post extends Timestamped{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;    //id
    @Column(name="title")
    String title;   //제목
    @Column(name = "writer_name", nullable = false)
    String writerName;//작성자명
    @Column(name ="contents",nullable = false, length = 500)
    String contents;//작성내용
    @Column(name="password",nullable = false)
    String password;//비밀번호


    public Post(PostRequestDto requestDto) {
        this.title = requestDto.getTitle();
        this.writerName = requestDto.getWriterName();
        this.contents = requestDto.getContents();
        this.password = requestDto.getPassword();
    }

    public void update(PostRequestDto requestDto) {
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
