package com.newsfeed.fakebook.dto.userDto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class LoginRequestDto {
	private String email;
	private String password;
}