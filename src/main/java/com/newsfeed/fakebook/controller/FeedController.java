package com.newsfeed.fakebook.controller;

import com.newsfeed.fakebook.service.FeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fakebook/feeds")
@RequiredArgsConstructor
public class FeedController {
    private final FeedService feedService;

    @PostMapping
    public ResponseEntity<Long> createFeed(@RequestHeader Long userId, @RequestBody String content) {
        Long feedId = feedService.createFeed(userId, content);
        return ResponseEntity.ok(feedId);
    }
}
