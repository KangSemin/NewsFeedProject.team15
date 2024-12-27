package com.newsfeed.fakebook.controller;

import com.newsfeed.fakebook.dto.feed.FeedRequestDto;
import com.newsfeed.fakebook.dto.feed.FeedResponseDto;
import com.newsfeed.fakebook.dto.feed.PagingFeedResponseDto;
import com.newsfeed.fakebook.service.FeedService;
import lombok.RequiredArgsConstructor;
import org.hibernate.query.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/fakebook/feeds")
@RequiredArgsConstructor
public class FeedController {
    private final FeedService feedService;

    @PostMapping
    public ResponseEntity<Long> createFeed(@RequestAttribute Long userId,
                                           @RequestBody FeedRequestDto request) {
        Long feedId = feedService.createFeed(userId, request);
        return ResponseEntity.ok(feedId);
    }

    @GetMapping
    public ResponseEntity<PagingFeedResponseDto> getAllFeeds(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(required = false) Integer size) {
        return ResponseEntity.ok(feedService.getAllFeeds(page,size));
    }

//    @GetMapping("/users/{userId}")
//    public ResponseEntity<PagingFeedResponseDto> getUserFeeds(@PathVariable Long userId,
//                                                              @RequestParam(defaultValue = "0") int page,
//                                                              @RequestParam(required = false) Integer size ) {
//
//        return ResponseEntity.ok(feedService.getUserFeeds(userId,page,size));
//    }

    @GetMapping("/{feedId}")
    public ResponseEntity<FeedResponseDto> getFeed(@PathVariable Long feedId) {
        return ResponseEntity.ok(feedService.getFeed(feedId));
    }


    @PutMapping("/{feedId}")
    public ResponseEntity<Void> updateFeed(@PathVariable Long feedId,
                                           @RequestBody FeedRequestDto request) {
        feedService.updateFeed(feedId, request);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{feedId}")
    public ResponseEntity<Void> deleteFeed(@PathVariable Long feedId) {

        feedService.deleteFeed(feedId);

        return ResponseEntity.ok().build();
    }
}
