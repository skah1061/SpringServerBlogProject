package com.sparta.blog.service;

import com.sparta.blog.Entity.User;
import com.sparta.blog.dto.UserRequestDto;
import com.sparta.blog.jwt.JwtUtil;
import com.sparta.blog.repository.UserRepository;
import io.jsonwebtoken.Jwt;
import jakarta.servlet.http.HttpServletResponse;
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
    private final JwtUtil jwtUtil;
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil=jwtUtil;
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
//    public void loginUser(UserRequestDto userRequestDto, HttpServletResponse httpServletResponse){
//        //로그인 기능
//        String username = userRequestDto.getUsername();
//        String password = userRequestDto.getPassword();
//
//        User user = userRepository.findByUsername(username).orElseThrow(
//                () -> new IllegalArgumentException("해당계정은 존재하지 않습니다.")
//        );
//        if(!passwordEncoder.matches(password,user.getPassword())){
//            throw new IllegalArgumentException("비밀번호가 틀립니다.");
//        }
//        String token = jwtUtil.createToken(user.getUsername());
//        jwtUtil.addJwtToCookie(token, httpServletResponse);
//
//    }
}
