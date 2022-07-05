package com.example.goguma.controller;

import com.example.goguma.dto.*;
import com.example.goguma.service.AuthService;
import com.example.goguma.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    //회원 로그인
    @PostMapping("user/login1")
    @ResponseBody
    public String login(@RequestBody @Valid UserRequestDto.LoginDto loginDto) { //@Valid는 LoginDto에 모든 칸이 채워졌는지 검사
        return authService.login(loginDto);
    }

    // 회원 로그인 페이지
    @GetMapping("/user/login")
    public String login() {
        return "login";
    }

    @GetMapping("/user/login/error")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "login";
    }

    // 회원 가입 페이지
    @GetMapping("/user/signup")
    public String signup() {
        return "signup";
    }

    // 회원 가입 요청 처리
    @PostMapping("/user/signup")
    public String registerUser(SignupRequestDto requestDto) {
        userService.registerUser(requestDto);
        return "redirect:/";
    }

    @GetMapping("/user/forbidden")
    public String forbidden() {
        return "forbidden";
    }

    @GetMapping("/user/kakao/callback")
    public String kakaoLogin(String code) {
        // authorizedCode: 카카오 서버로부터 받은 인가 코드
        userService.kakaoLogin(code);

        return "redirect:/";
    }

    @ResponseBody
    @PostMapping ("/user/sign_up/check_dup")
    public int checkUser(CheckRequestDto requestDto){
        int result = userService.checkUser(requestDto);
        System.out.println(result);
        return result;
    }

    @ResponseBody
    @PostMapping("/user/sign_up/check_dup_nick")
    public int checkNickname (CheckRequestDto requestDto){
        int result = userService.checkNickname(requestDto);
        System.out.println(result);
        return result;
    }

    /**
     * user가 작성한 게시물 리스팅
     * @param userId
     * @return 작성한 게시물 리스트
     */
    @ResponseBody
    @GetMapping("/user/get_write_posts/{userId}")
    public List<PostResponseDto> getMyPosts(@PathVariable Long userId) {
        return userService.getMyPosts(userId);
    }

    /**
     * user의 관심 상품 리스팅
     * @param userId
     * @return 관심 상품 리스트
     */
    @ResponseBody
    @GetMapping("/user/get_like_posts/{userId}")
    public List<PostResponseDto> getLikePosts(@PathVariable Long userId) {
        return userService.getLikePosts(userId);
    }
}
