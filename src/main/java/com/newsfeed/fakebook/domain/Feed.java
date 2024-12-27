package com.newsfeed.fakebook.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity @Table(name = "feeds") @Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Feed {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long feedId;

	@Column(nullable = false)
	private String content;

	@CreatedDate
	private LocalDateTime postedTime;

	@LastModifiedDate
	private LocalDateTime modifiedTime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	@Builder
	public Feed(User user, String content) {
		this.user = user;
		this.content = content;
	}

	public void update(String contents) {
		this.content = contents;
	}
}

