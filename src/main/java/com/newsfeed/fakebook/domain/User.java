package com.newsfeed.fakebook.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;

	@Email
	@Column(unique = true)
	private String email;

	private String password;
	private String username;

	@CreatedDate
	private LocalDateTime registerDate;
	@CreatedDate
	private LocalDateTime updatedDate;

	private String profileImage;

	private boolean deleted = false;
	private LocalDateTime deletedDate;


	@Builder
	public User(String email, String password, String username) {
		this.email = email;
		this.password = password;
		this.username = username;
	}

	public void update(String email, String password, String username) {
		this.email = email!=null ? email : this.email;
		this.username = username!=null ? username : this.username;
		this.password = password!=null ? password : this.password;
	}

	public void markAsDeleted() {
		this.deleted = true;
		this.deletedDate = LocalDateTime.now();
		this.username = "[Deleted User]";
		this.email = "";
	}

//	public String getDisplayName() {
//		return deleted ? "[Deleted User]" : username;
//	}


}
