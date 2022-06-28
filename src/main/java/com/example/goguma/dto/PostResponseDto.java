package com.example.goguma.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class PostResponseDto {
    private Long postId;
    private String title;
    private int price;
    private String address;
    private int likeCount;
    private String content;
    private List<PostImgResponseDto> postImgs = new ArrayList<>();

    private Long writeUserId;
    private String writerNickname;
    private String writerProfile;

    private String date;

    public PostResponseDto(Long postId, String title, int price, String address, int likeCount) {
        this.postId = postId;
        this.title = title;
        this.price = price;
        this.address = address;
        this.likeCount = likeCount;
    }

    public PostResponseDto(Long postId, String title, int price, String address, int likeCount, String content, Long writeUserId, String writerNickname, String writerProfile, String date) {
        this.postId = postId;
        this.title = title;
        this.price = price;
        this.address = address;
        this.likeCount = likeCount;
        this.content = content;
        this.writeUserId = writeUserId;
        this.writerNickname = writerNickname;
        this.writerProfile = writerProfile;
        this.date = date;
    }
}