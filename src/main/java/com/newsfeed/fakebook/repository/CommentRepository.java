package com.newsfeed.fakebook.repository;

import com.newsfeed.fakebook.domain.Comment;
import com.newsfeed.fakebook.domain.Feed;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query("select c from Comment c where c.feed.feedId = :feedId order by c.writtenTime desc")
    List<Comment> findByFeedId(@Param("feedId") Long feedId);
}
