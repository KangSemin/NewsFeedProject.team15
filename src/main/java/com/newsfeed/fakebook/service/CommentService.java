package com.newsfeed.fakebook.service;

import com.newsfeed.fakebook.domain.Comment;
import com.newsfeed.fakebook.domain.Feed;
import com.newsfeed.fakebook.domain.User;
import com.newsfeed.fakebook.dto.comment.CommentRequestDto;
import com.newsfeed.fakebook.dto.comment.CommentResponseDto;
import com.newsfeed.fakebook.repository.CommentRepository;
import com.newsfeed.fakebook.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
//    private final FeedRepository feedRepository;

    public Long createComment(Long userId, Long feedId, CommentRequestDto requestDto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("유저를 찾을 수 없습니다."));
        Feed feed = feedRepository.findById(feedId)
                .orElseThrow(() -> new IllegalArgumentException("게시물을 찾을 수 없습니다."));

        Comment comment = Comment.builder()
                .user(user)
                .feed(feed)
                .content(requestDto.getContent())
                .build();

        return commentRepository.save(comment).getCommentId();
    }

    public List<CommentResponseDto> getCommentByFeed(Long feedId) {
        return commentRepository.findByFeedId(feedId).stream()
                .map(CommentResponseDto::new)
                .collect(Collectors.toList());
    }
}
