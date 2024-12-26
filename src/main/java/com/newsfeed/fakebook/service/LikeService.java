package com.newsfeed.fakebook.service;

import com.newsfeed.fakebook.domain.Feed;
import com.newsfeed.fakebook.domain.Like;
import com.newsfeed.fakebook.domain.User;
import com.newsfeed.fakebook.dto.LikeResponseDto;
import com.newsfeed.fakebook.repository.FeedRepository;
import com.newsfeed.fakebook.repository.LikeRepository;
import com.newsfeed.fakebook.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor

public class LikeService {
    private final FeedRepository feedRepository;
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;

    public void save(Long likeId, Long userId, Long feedId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("user name이 없음" + userId));

        Feed feed = feedRepository.findById(feedId)
                .orElseThrow(() -> new RuntimeException("feed name이 없음" + feedId));

        Like like = Like.builder().feed(feed).user(user).build();

        //        Like like = new Like();
        //        like.setUser(user);
        //        like.setFeed(feed);
        // lllll

        likeRepository.save(like);
    }


    public LikeResponseDto findById(Long likeId) {
        Like findlike = likeRepository.findById(likeId)
                .orElseThrow(() -> new RuntimeException("feed name이 없음" + likeId));

        return new LikeResponseDto(findlike.getUsername(), findlike.getProfileImage());
    }


}
//new Like();