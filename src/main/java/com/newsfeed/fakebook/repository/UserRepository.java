package com.newsfeed.fakebook.repository;

import com.newsfeed.fakebook.domain.Feed;
import com.newsfeed.fakebook.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findUserByUsername(Long userId);

}
