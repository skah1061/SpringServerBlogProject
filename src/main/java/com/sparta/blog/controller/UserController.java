package com.sparta.blog.controller;


import com.sparta.blog.dto.UserRequestDto;
import com.sparta.blog.jwt.JwtUtil;
import com.sparta.blog.security.UserDetailsImpl;
import com.sparta.blog.service.UserService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class UserController {
    private final UserService userService;
    private final JwtUtil jwtUtil;
    public UserController(UserService userService,JwtUtil jwtUtil){
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/auth/signup")
    public String signupUser(@RequestBody UserRequestDto userRequestDto){
        String respone = userService.signupUser(userRequestDto);
        return respone;
    }
//    @PostMapping("/auth/login")
//    public String loginUser(@RequestBody UserRequestDto userRequestDto,HttpServletResponse httpServletResponse){
//        try {
//            userService.loginUser(userRequestDto,httpServletResponse);
//        } catch (Exception e) {
//            return "redirect:/api/auth/login?error";
//        }
//
//        return "로그인 성공";
//    }
    @GetMapping("/create-jwt")
    public String createJwt(HttpServletResponse res) {
        // Jwt 생성
        String token = jwtUtil.createToken("JungHoon");

        // Jwt 쿠키 저장
        jwtUtil.addJwtToCookie(token, res);

        return "createJwt : " + token;
    }

    @GetMapping("/get-jwt")
    public String getJwt(@CookieValue(JwtUtil.AUTHORIZATION_HEADER) String tokenValue) {
        // JWT 토큰 substring
        String token = jwtUtil.substringToken(tokenValue);
        // 토큰 검증
        if(!jwtUtil.validateToken(token)){
            throw new IllegalArgumentException("Token Error");
        }

        // 토큰에서 사용자 정보 가져오기
        Claims info = jwtUtil.getUserInfoFromToken(token);
        // 사용자 username
        String username = info.getSubject();
        System.out.println("username = " + username);


        return "getJwt : " + username ;
    }
    @GetMapping("/")
    public  String home(Model model, @AuthenticationPrincipal UserDetailsImpl userDetails){
        model.addAttribute("username",userDetails.getUser());
        return "index";
    }
    @GetMapping("/api/user/login-page")
    public  String loginHome(){
        return "홈";
    }
}
