package com.newsfeed.fakebook.controller;

import com.newsfeed.fakebook.service.LikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fakebook/feeds")
@RequiredArgsConstructor
public class LikeController {

	private final LikeService likeService;

	@PostMapping("/{feedId}/like")
	public ResponseEntity<Void> toggleLike(@RequestAttribute Long userId, @PathVariable Long feedId) {

		likeService.toggleLike(userId,feedId);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
}
