package com.example.goguma.model;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@NoArgsConstructor
@Getter
public class Order extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_id")
    private Post post;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="user_id")
    private User user;

    private String startDate;

    private String endDate;

    private int price;

    private boolean isChecked;

    public Order(String startDate, String endDate, int price) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.isChecked = false;
    }

    public void addCustomer(User user) {
        this.user = user;
    }

    public void addPost(Post post) {
        this.post = post;
    }

    public void checkOrder(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public static Order makeOrder(Post post) {
        //판매완료
        post.sold(true);

        //날짜 필터링 - startDate, endDate
        String[] dateSplit = post.getDate().split("-");
        String startDate = dateSplit[0].trim();
        String endDate = dateSplit[1].trim();

        //가격
        int price = post.getPrice();

        return new Order(startDate, endDate, price);
    }
}
