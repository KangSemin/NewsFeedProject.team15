package com.newsfeed.fakebook.service;

import com.newsfeed.fakebook.domain.Report;
import com.newsfeed.fakebook.domain.User;
import com.newsfeed.fakebook.repository.ReportRepository;
import com.newsfeed.fakebook.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReportService {
    
    private final ReportRepository reportRepository;
    private final UserRepository userRepository;
    
    public void reportUser(Long reporterId, Long reportedUserId, String reason) {
        User reporter = userRepository.findById(reporterId)
                .orElseThrow(() -> new IllegalArgumentException("신고자를 찾을 수 없습니다."));
        User reportedUser = userRepository.findById(reportedUserId)
                .orElseThrow(() -> new IllegalArgumentException("신고 대상자를 찾을 수 없습니다."));
                
        if (reporter.equals(reportedUser)) {
            throw new IllegalStateException("자기 자신을 신고할 수 없습니다.");
        }
        
        Report report = new Report(reporter, reportedUser, reason);
        reportRepository.save(report);
    }
} 