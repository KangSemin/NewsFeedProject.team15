package com.newsfeed.fakebook.controller;


import com.newsfeed.fakebook.domain.User;
import com.newsfeed.fakebook.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fakebook/users")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;


	@PostMapping("/signup")
	public ResponseEntity<Long> signup(HttpServletResponse response) {
		Long userId  ;
		return ResponseEntity.ok(userId);
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(HttpSession session) {

		User user ;
		session.setAttribute("userId",user);
		return ResponseEntity.ok("Login success!");
	}

	@PostMapping("logout")
	public ResponseEntity<String> logout(HttpSession session) {

		session.invalidate();
		return ResponseEntity.ok("Logout success!");
	}

	@GetMapping
	public ResponseEntity<> getUser(@CookieValue(required = false) Long userId) {



	}


}
