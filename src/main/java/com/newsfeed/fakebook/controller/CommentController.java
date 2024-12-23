package com.newsfeed.fakebook.controller;

import com.newsfeed.fakebook.dto.comment.CommentRequestDto;
import com.newsfeed.fakebook.dto.comment.CommentResponseDto;
import com.newsfeed.fakebook.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fakebook/feeds/{feedId}/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<Long> createComment(
            @PathVariable Long feedId,
            @CookieValue(required = false) Long userId,
            @RequestBody CommentRequestDto requestDto) {
        if (userId == null) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }
        Long commentId = commentService.createComment(userId, feedId, requestDto);
        return ResponseEntity.ok(commentId);
    }

    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> getCommentByFeed(@PathVariable Long feedId) {
        List<CommentResponseDto> comment = commentService.getCommentByFeed(feedId);
        return ResponseEntity.ok(comment);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<Void> updateComment(
            @PathVariable Long commmentId,
            @RequestBody CommentRequestDto requestDto) {
        commentService.updateComment(commmentId, requestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok().build();
    }
}
