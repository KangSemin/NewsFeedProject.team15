package com.newsfeed.fakebook.repository;

import com.newsfeed.fakebook.domain.Report;
import com.newsfeed.fakebook.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findByReporter(User reporter);
    List<Report> findByReportedUser(User reportedUser);
    List<Report> findByStatus(Report.ReportStatus status);
}
