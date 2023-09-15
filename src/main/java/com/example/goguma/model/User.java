package com.example.goguma.model;

import com.example.goguma.dto.ProfileUpdateDto;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@Setter
@Getter
@NoArgsConstructor
@Entity // DB 테이블 역할을 합니다.
public class User extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    // 반드시 값을 가지도록 합니다.
    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)
    private String address;

    private String profilePic;

    private String profileInfo;

    private String accessToken;

    private String refreshToken;

    private Long kakaoId;

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Post> posts = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Like> likes = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "sender", cascade = CascadeType.ALL)
    private List<ChatMessage> messages  = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ChatRoom> rooms = new ArrayList<>();

    public void addPost(Post post) {
        posts.add(post);
        post.addUser(this);
    }

    public void addRoom(ChatRoom chatRoom) {
        rooms.add(chatRoom);
        chatRoom.addUser(this);
    }

    public void addLike(Like like) {
        likes.add(like);
    }

    public void addOrder(Order order) {
        orders.add(order);
        order.addCustomer(this);
    }

    public void addMessages(ChatMessage chatMessage) {
        messages.add(chatMessage);
    }

    public User(String username, String password, String nickname, String address) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.address = address;
        this.kakaoId = null;
        this.profilePic = null;
        this.profileInfo = null;
    }

    public User(String username, String password, String nickname, Long kakaoId, String profilePic) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.address = null;
        this.kakaoId = kakaoId;
        this.profilePic = profilePic;
        this.profileInfo = null;
    }


    public void update(ProfileUpdateDto profileUpdateDto,String profilePic){
        this.nickname = profileUpdateDto.getNickname();
        this.address = profileUpdateDto.getAddress();
        this.profilePic = profilePic;
        this.profileInfo = profileUpdateDto.getProfileInfo();
    }

    public void pwUpdate(String password){
        this.password = password;
    }

    public void accessToken(String accessToken){
        this.accessToken = accessToken;
    }

    public void refreshToken(String refreshToken){
        this.refreshToken = refreshToken;
    }
}

