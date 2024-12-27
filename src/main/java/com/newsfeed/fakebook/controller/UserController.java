package com.newsfeed.fakebook.controller;

import com.newsfeed.fakebook.config.JwtConfig;
import com.newsfeed.fakebook.domain.User;
import com.newsfeed.fakebook.dto.user.LoginRequestDto;
import com.newsfeed.fakebook.dto.user.UserRequestDto;
import com.newsfeed.fakebook.dto.user.UserResponseDto;
import com.newsfeed.fakebook.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/fakebook/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	private final JwtConfig jwtConfig;

	@PostMapping("/signup")
	public ResponseEntity<Map<String, Object>> signup(@RequestBody UserRequestDto request) {
		Long userId = userService.createUser(request);
		String token = jwtConfig.generateToken(userId);
		
		Map<String, Object> response = new HashMap<>();
		response.put("userId", userId);
		response.put("token", token);
		
		return ResponseEntity.ok(response);
	}

	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequestDto request) {
		User user = userService.login(request);
		String token = jwtConfig.generateToken(user.getUserId());
		
		Map<String, Object> response = new HashMap<>();
		response.put("userId", user.getUserId());
		response.put("token", token);
		response.put("message", "로그인 성공!");
		
		return ResponseEntity.ok(response);
	}

	@PostMapping("/logout")
	public ResponseEntity<Void> logout(@RequestAttribute Long userId, HttpServletRequest request) {
		request.removeAttribute("userId");
		return ResponseEntity.ok().build();
	}

	@GetMapping
	public ResponseEntity<UserResponseDto> getUser(@RequestAttribute Long userId) {
		UserResponseDto user = userService.getUser(userId);
		return ResponseEntity.ok(user);
	}

	@PutMapping
	public ResponseEntity<Void> updateUser(@RequestAttribute Long userId,
	                                     @RequestBody UserRequestDto request) {
		userService.updateUser(userId, request);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping
	public ResponseEntity<Void> deleteUser(@RequestAttribute Long userId) {
		userService.deleteUser(userId);
		return ResponseEntity.ok().build();
	}
}
