package com.newsfeed.fakebook.dto.user;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserRequestDto {
	private String email;
	private String password;
	private String username;
}
