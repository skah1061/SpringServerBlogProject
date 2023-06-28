package com.sparta.blog.Entity;

import com.sparta.blog.dto.PostDetailDto;
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
    @Column(name="password",nullable = true)
    String password;//비밀번호
    @Column(name="user_id",nullable = false)
    long user_id;
//    @ManyToOne
//    @JoinColumn(name = "user_id", nullable = false)
//    User user;

    public Post(PostRequestDto requestDto, String username , long user_id) {
        this.title = requestDto.getTitle();
        this.contents = requestDto.getContents();
        this.writerName = username;
        this.password  = "1";
        this.user_id = user_id;
    }

    public void update(PostDetailDto detailDto) {
            this.title = detailDto.getTitle();
            this.writerName = detailDto.getWriterName();
            this.contents = detailDto.getContents();
    }
}
