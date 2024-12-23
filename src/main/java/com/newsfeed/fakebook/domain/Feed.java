package com.newsfeed.fakebook.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "feeds")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Feed {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long feedId;

	@Column(nullable = false)
	private String content;

	private Integer likecount;

	private Integer pageable;

	@CreatedDate
	private LocalDateTime postedTime;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")

	private User user;

	@Builder
	public Feed(User user, String content, Integer likecount, Integer pageable) {
		this.user = user;
		this.content = content;
		this.likecount = likecount;
		this.pageable = pageable;
	}

	public void update(String contents) {
		this.content = contents;
	}
}

