package com.newsfeed.fakebook.dto.comment;

import com.newsfeed.fakebook.domain.Comment;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CommentResponseDto {
    private final Long commentId;
    private final String username;
    private final String content;
    private final LocalDateTime writtenTime;

    public CommentResponseDto(Comment comment) {
        this.commentId = comment.getCommentId();
        this.username = comment.getUser().getUsername();
        this.content = comment.getContent();
        this.writtenTime = comment.getWrittenTime();
    }
}
