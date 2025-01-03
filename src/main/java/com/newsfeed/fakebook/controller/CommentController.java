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
    public ResponseEntity<Long> createComment(@PathVariable Long feedId,
                                              @RequestAttribute Long userId,
                                              @RequestBody CommentRequestDto requestDto) {
        Long commentId = commentService.createComment(userId, feedId, requestDto);
        return ResponseEntity.ok(commentId);
    }

    @GetMapping
    public ResponseEntity<List<CommentResponseDto>> getCommentByFeed(@PathVariable Long feedId) {
        List<CommentResponseDto> comments = commentService.getCommentByFeed(feedId);
        return ResponseEntity.ok(comments);
    }

    @PutMapping("/{commentId}")
    public ResponseEntity<Void> updateComment(@PathVariable Long feedId,
                                              @PathVariable Long commentId,
                                              @RequestAttribute Long userId,
                                              @RequestBody CommentRequestDto requestDto) throws IllegalAccessException {
        commentService.updateComment(userId, feedId, commentId, requestDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long feedId,
                                              @PathVariable Long commentId,
                                              @RequestAttribute Long userId) throws IllegalAccessException {
        commentService.deleteComment(userId, feedId, commentId);
        return ResponseEntity.ok().build();
    }
}
