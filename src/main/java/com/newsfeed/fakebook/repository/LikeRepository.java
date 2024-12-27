package com.newsfeed.fakebook.repository;

import com.newsfeed.fakebook.domain.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface LikeRepository extends JpaRepository<Like, Long> {

	Optional<Like> findByUser_UserIdAndFeed_FeedId(Long userId, Long feedId);

	Long countByFeed_FeedId(Long feedFeedId);
}

