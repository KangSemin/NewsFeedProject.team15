package com.newsfeed.fakebook.service;

import com.newsfeed.fakebook.domain.Feed;
import com.newsfeed.fakebook.domain.User;
import com.newsfeed.fakebook.repository.FeedRepository;
import com.newsfeed.fakebook.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FeedService {
    private final UserRepository userRepository;
    private final FeedRepository feedRepository;

    public Long createFeed(Long userId, String content) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));
        Feed feed = Feed.builder()
                .user(user)
                .content(content)
                .likecount(0)
                .pageable(1)
                .build();
        return feedRepository.save(feed).getFeedId();
    }

    public List<FeedDto> getAllFeeds() {
        List<Feed> all = feedRepository.findAll();
    }
}
