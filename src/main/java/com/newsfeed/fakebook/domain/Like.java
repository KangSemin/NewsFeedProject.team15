package com.newsfeed.fakebook.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "likes")
@NoArgsConstructor

public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long likeId;

    @Builder
    public Like( User user, Feed feed ) {
        this.user= user;
        this.feed = feed;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "feed_id")
    private Feed feed;

    private String username;

    private String profileImage;

    public void LikeResponseDto (String username, String profileImage) {
        this.username = username;
        this.profileImage = profileImage;
    }


}
