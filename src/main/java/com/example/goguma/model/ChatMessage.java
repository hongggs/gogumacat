package com.example.goguma.model;

import com.example.goguma.security.msg.Aes128;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ChatMessage extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "msg_id")
    Long id;

    //채팅방 ID
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "room_id", nullable = false)
    private ChatRoom room;

    //보내는 사람
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User sender;

    //내용
    private String message;


    @Enumerated(value = EnumType.STRING)
    private MessageType type;

    public void addRoom(ChatRoom room) {
        this.room = room;
        room.addMessage(this);
    }

    public void addSender(User sender) {
        this.sender = sender;
        sender.addMessages(this);
    }

    public ChatMessage(MessageType type, String message) {
        this.type = type;
        this.message = Aes128.getAES128encode(message);
    }
}
