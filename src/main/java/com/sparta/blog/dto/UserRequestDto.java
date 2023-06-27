package com.sparta.blog.dto;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {
    @Size(min=4,max=10)
    @Pattern(regexp = "^[a-z0-9]$")
    String username;
    @Size(min=8,max=15)
    @Pattern(regexp = "^[a-zA-Z0-9]$")
    String password;
    //Pattern이용하여 구현하기
}
