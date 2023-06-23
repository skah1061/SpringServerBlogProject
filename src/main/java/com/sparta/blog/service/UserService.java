package com.sparta.blog.service;

import com.sparta.blog.Entity.User;
import com.sparta.blog.dto.UserRequestDto;
import com.sparta.blog.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }
    public String signupUser(UserRequestDto userRequestDto) {
        //회원가입 기능
        log.info("userReqeustDto"+userRequestDto);
        String username = userRequestDto.getUsername();
        String password = passwordEncoder.encode(userRequestDto.getPassword());

        Optional<User> checkUsername = userRepository.findByUsername(username);
        if(checkUsername.isPresent()){
            throw new IllegalArgumentException("중복된 사용자가 존재합니다.");
        }

        User user = new User(username,password);
        userRepository.save(user);

        return "회원가입 완료";
    }
    public String loginUser(UserRequestDto userRequestDto){
        //로그인 기능
        return "로그인 성공";
    }
}
