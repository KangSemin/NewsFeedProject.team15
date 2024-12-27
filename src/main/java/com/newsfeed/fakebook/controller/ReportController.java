package com.newsfeed.fakebook.controller;

import com.newsfeed.fakebook.service.ReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fakebook/users")
@RequiredArgsConstructor
public class ReportController {
    
    private final ReportService reportService;
    
    @PostMapping("/{reportedUserId}/report")
    public ResponseEntity<Void> reportUser(@RequestAttribute Long userId,
                                           @PathVariable Long reportedUserId,
                                           @RequestParam String reason) {
        if (userId == null) {
            throw new IllegalArgumentException("로그인이 필요합니다.");
        }
        reportService.reportUser(userId, reportedUserId, reason);
        return ResponseEntity.ok().build();
    }
} 