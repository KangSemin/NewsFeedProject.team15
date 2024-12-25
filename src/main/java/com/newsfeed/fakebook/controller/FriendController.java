package com.newsfeed.fakebook.controller;

import com.newsfeed.fakebook.domain.Friend;
import com.newsfeed.fakebook.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fakebook/friends")
@RequiredArgsConstructor
public class FriendController {
    
    private final FriendService friendService;
    
    @PostMapping("/request/{toUserId}")
    public ResponseEntity<Void> sendFriendRequest(
            @RequestAttribute Long userId,
            @PathVariable Long toUserId) {
        if (userId == null) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }
        friendService.sendFriendRequest(userId, toUserId);
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/accept")
    public ResponseEntity<Void> acceptFriendRequest(
            @RequestAttribute Long userId,
            @RequestParam Long fromUserId) {
        if (userId == null) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }
        friendService.acceptFriendRequest(fromUserId, userId);
        return ResponseEntity.ok().build();
    }
    
    @PostMapping("/reject")
    public ResponseEntity<Void> rejectFriendRequest(
            @RequestAttribute Long userId,
            @RequestParam Long fromUserId) {
        if (userId == null) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }
        friendService.rejectFriendRequest(fromUserId, userId);
        return ResponseEntity.ok().build();
    }

//    @GetMapping
//    public ResponseEntity<List<Friend>> getAllFriends(
//            @RequestAttribute Long userId) {
//        if (userId == null) {
//            throw new IllegalArgumentException("로그인이 필요합니다.");
//        }
//        return ResponseEntity.ok(friendService.getAllFriends(userId));
//    }
}