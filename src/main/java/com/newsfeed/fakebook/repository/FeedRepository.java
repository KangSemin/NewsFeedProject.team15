package com.newsfeed.fakebook.repository;

import com.newsfeed.fakebook.domain.Feed;
import com.newsfeed.fakebook.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeedRepository extends JpaRepository<Feed, Long> {
    Optional<Feed> findById(Long feedId);
}
