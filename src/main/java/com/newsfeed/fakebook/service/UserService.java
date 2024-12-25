package com.newsfeed.fakebook.service;


import com.newsfeed.fakebook.domain.User;
import com.newsfeed.fakebook.dto.UserRequestDto;
import com.newsfeed.fakebook.dto.UserResponseDto;
import com.newsfeed.fakebook.repository.LikeRepository;
import com.newsfeed.fakebook.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;


	public Long createUser(String email, String password, String username) {

		String encodedPassword = "대충 비밀번호 인코딩하는 로직";

		User user = User.builder()
				.email(email)
				.password(encodedPassword)
				.username(username)
				.build();

		return userRepository.save(user).getUserId();

	}

	public UserResponseDto getUser(Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(()-> new IllegalArgumentException("cannot find user."));

		return new UserResponseDto(user);
	}

	public void updateUser(Long userId, UserRequestDto requestDto) {
		User user = userRepository.findById(userId)
				.orElseThrow(()-> new IllegalArgumentException("cannot find user."));

		String password = requestDto.getPassword() != null ? "대충 인코딩한 패스워드" : user.getPassword();
		user.update(requestDto.getEmail(), password, requestDto.getUsername());
	}

	public void deleteUser(Long userId) {
		userRepository.findById(userId).get().markAsDeleted();

	}



}
