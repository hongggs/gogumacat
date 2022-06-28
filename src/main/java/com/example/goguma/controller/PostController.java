package com.example.goguma.controller;

import com.example.goguma.dto.PostRequestDto;
import com.example.goguma.dto.PostResponseDto;
import com.example.goguma.model.Post;
import com.example.goguma.repository.LikeRepository;
import com.example.goguma.security.UserDetailsImpl;
import com.example.goguma.service.LikeService;
import com.example.goguma.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;
    private final LikeRepository likeRepository;

    @GetMapping("/all")
    @ResponseBody
    public List<PostResponseDto> getAllPosts() {
        return postService.getAllPosts();
    }

    /**
     * 회원이 게시물을 선택하면 상세페이지를 보여준다.
     * @param postId
     * @param model // response: 게시물 정보
     * @param userDetails // 로그인 계정 정보
     * @return post.html
     */
    @RequestMapping("/post/{postId}")
    public String getOnePost(@PathVariable Long postId, Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        model.addAttribute("post", postService.getOnePost(postId));
        model.addAttribute("nickname", userDetails.getNickname());
        model.addAttribute("userId", userDetails.getId());
        model.addAttribute("likeByMe", likeRepository.existsByuserIdAndPostId(userDetails.getId(), postId));
        return "post";
    }


    @PostMapping(value = "/user_post", consumes = {"multipart/form-data"})
    @ResponseBody
    public Post createPost(@ModelAttribute PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails){
        Post temp = postService.registerPost(postRequestDto, userDetails.getId());

        return temp;
    }

    @DeleteMapping("post/delete/{postId}")
    public String deletePost(@PathVariable Long postId){
        postService.deletePost(postId);
        return "redirect:/";
    }

    @RequestMapping("/posting_update/{postId}")
    public String updatePostPage(@PathVariable Long postId, Model model, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        model.addAttribute("post", postService.getOnePost(postId));
        return "posting_update";
    }

    @PostMapping(value = "/post/update/{postId}", consumes = {"multipart/form-data"})
    public String updatePost(@PathVariable Long postId, @ModelAttribute PostRequestDto postRequestDto) {
        postService.updatePost(postId, postRequestDto);
        return "redirect:/post/" + postId;
    }
}
