package com.newsfeed.fakebook.dto.feed;

import com.newsfeed.fakebook.domain.Feed;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class FeedResponseDto {
    private final Long feedId;
    private final String content;
    private final Long commentCount;
    private final Long likeCount;
    private final LocalDateTime postedTime;
    private final LocalDateTime modifiedTime;
    private final String username;

    public FeedResponseDto (Feed feed, Long commentCount, Long likeCount) {
        this.feedId=feed.getFeedId();
        this.content = feed.getContent();
        this.commentCount = commentCount;
        this.likeCount=likeCount;
        this.postedTime = feed.getPostedTime();
        this.modifiedTime=feed.getModifiedTime();
        this.username = feed.getUser().getUsername();
    }
}
