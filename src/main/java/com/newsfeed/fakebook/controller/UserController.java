package com.newsfeed.fakebook.controller;


import com.newsfeed.fakebook.domain.User;
import com.newsfeed.fakebook.dto.userDto.LoginRequestDto;
import com.newsfeed.fakebook.dto.userDto.UserRequestDto;
import com.newsfeed.fakebook.dto.userDto.UserResponseDto;
import com.newsfeed.fakebook.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/fakebook/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;


	@PostMapping("/signup")
	public ResponseEntity<Long> signup( @RequestBody UserRequestDto request, HttpServletResponse response) {

		Long userId = userService.createUser(request) ;
		setSessionCookie(userId,response);
		return ResponseEntity.ok(userId);
	}

	@PostMapping("/login")
	public ResponseEntity<String> login( @RequestBody LoginRequestDto request, HttpSession session) {

		User user = userService.login(request) ;
		session.setAttribute("userId",user);
		return ResponseEntity.ok("로그인 성공!");
	}

	@PostMapping("/logout")
	public ResponseEntity<String> logout(HttpSession session) {

		session.invalidate();
		return ResponseEntity.ok("로그아웃 성공!");
	}

	@GetMapping
	public ResponseEntity<UserResponseDto> getUser(@CookieValue(required = false) Long userId) {
		if (userId == null ) {
			throw new IllegalArgumentException("로그인이 필요합니다.");
		}
		UserResponseDto user = userService.getUser(userId);
		return ResponseEntity.ok(user);
	}

	@PutMapping
	public ResponseEntity<Void> updateUser(@CookieValue(required = false) Long userId,
	                                       @RequestBody UserRequestDto request,
	                                       HttpServletResponse response) {
		if (userId == null ) {
			throw new IllegalArgumentException("로그인이 필요합니다.");
		}
		userService.updateUser(userId,request);
		Cookie cookie = new Cookie("userId", null);
		cookie.setMaxAge(0);
		response.addCookie(cookie);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping
	public ResponseEntity<Void> deleteUser(@CookieValue(required = false) Long userId,
	                                       HttpSession session,
	                                       HttpServletResponse response) {
		if (userId == null) {
			throw new IllegalArgumentException("로그인이 필요합니다.");
		}

		userService.deleteUser(userId);
		session.invalidate();

		Cookie cookie = new Cookie("userId", null);
		cookie.setMaxAge(0);
		cookie.setPath("/");
		response.addCookie(cookie);

		return ResponseEntity.ok().build();
	}

	private void setSessionCookie(Long userId, HttpServletResponse response) {
		Cookie cookie = new Cookie("userId", userId.toString());
		cookie.setMaxAge(1800);
		cookie.setPath("/");
		response.addCookie(cookie);
	}

}
