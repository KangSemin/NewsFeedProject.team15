package com.newsfeed.fakebook.repository;

import com.newsfeed.fakebook.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {


}
