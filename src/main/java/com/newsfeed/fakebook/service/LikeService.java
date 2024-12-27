package com.newsfeed.fakebook.service;

import com.newsfeed.fakebook.domain.Feed;
import com.newsfeed.fakebook.domain.Like;
import com.newsfeed.fakebook.domain.User;
import com.newsfeed.fakebook.dto.like.LikeResponseDto;
import com.newsfeed.fakebook.repository.FeedRepository;
import com.newsfeed.fakebook.repository.LikeRepository;
import com.newsfeed.fakebook.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor

public class LikeService {
    private final FeedRepository feedRepository;
    private final LikeRepository likeRepository;
    private final UserRepository userRepository;

    public void toggleLike(Long userId, Long feedId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("user name이 없음" + userId));

        Feed feed = feedRepository.findById(feedId)
                .orElseThrow(() -> new RuntimeException("feed name이 없음" + feedId));


        Optional<Like> countByFeedId = likeRepository.findByUser_UserIdAndFeed_FeedId(userId,feedId);

        countByFeedId.ifPresentOrElse(
		        likeRepository::delete,
                () -> likeRepository.save(Like.builder().feed(feed).user(user).build())
                );
        //        Like like = new Like();
        //        like.setUser(user);
        //        like.setFeed(feed);

    }


//    public LikeResponseDto findById(Long likeId) {
//        Like findlike = likeRepository.findById(likeId)
//                .orElseThrow(() -> new RuntimeException("feed name이 없음" + likeId));
//
//        return new LikeResponseDto(findlike.getUsername(), findlike.getProfileImage());
//    }
//

}
//new Like();