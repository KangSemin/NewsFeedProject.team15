package com.newsfeed.fakebook.repository;

import com.newsfeed.fakebook.domain.Friend;
import com.newsfeed.fakebook.domain.FriendId;
import com.newsfeed.fakebook.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FriendRepository extends JpaRepository<Friend, FriendId> {
    
    @Query("SELECT f FROM Friend f WHERE (f.fromUser = :user OR f.toUser = :user) AND f.status = :status")
    List<Friend> findByUserAndStatus(@Param("user") User user, @Param("status") Friend.FriendStatus status);
    
    List<Friend> findByToUserAndStatus(User toUser, Friend.FriendStatus status);
    
    Optional<Friend> findByFromUserAndToUser(User fromUser, User toUser);
} 