package com.newsfeed.fakebook.dto.feedDto;

import com.newsfeed.fakebook.domain.Feed;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class FeedResponseDto {
    private final Long feedId;
    private final String username;
    private final String content;
    private final Integer likeCount;
    private final Integer pageable;
    private final LocalDateTime postedTime;

    public FeedResponseDto (Feed feed) {
        this.feedId = feed.getFeedId();
        this.username = feed.getUser().getUsername();
        this.content = feed.getContent();
        this.likeCount = feed.getLikecount();
        this.pageable = feed.getPageable();
        this.postedTime = feed.getPostedTime();
    }
}
