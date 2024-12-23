package com.newsfeed.fakebook.service;


import com.newsfeed.fakebook.config.PasswordEncoder;
import com.newsfeed.fakebook.domain.User;
import com.newsfeed.fakebook.dto.userDto.LoginRequestDto;
import com.newsfeed.fakebook.dto.userDto.UserRequestDto;
import com.newsfeed.fakebook.dto.userDto.UserResponseDto;
import com.newsfeed.fakebook.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public Long createUser(UserRequestDto request) {

		String encodedPassword = passwordEncoder.encode(request.getPassword());

		User user = User.builder()
				.email(request.getEmail())
				.password(encodedPassword)
				.username(request.getUsername())
				.build();

		return userRepository.save(user).getUserId();

	}

	public User login(LoginRequestDto requestDto) {
		User user = userRepository.findByEmail(requestDto.getEmail())
				.orElseThrow(() -> new IllegalArgumentException("등록되지 않은 이메일입니다."));


		if (!passwordEncoder.matches(requestDto.getPassword(), user.getPassword())) {
			throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
		}

		return user;
	}


	public UserResponseDto getUser(Long userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(()-> new IllegalArgumentException("유저를 찾을 수 없습니다."));

		return new UserResponseDto(user);
	}

	public void updateUser(Long userId, UserRequestDto requestDto) {
		User user = userRepository.findById(userId)
				.orElseThrow(()-> new IllegalArgumentException("유저를 찾을 수 없습니다."));

		String password = requestDto.getPassword() != null ?
				passwordEncoder.encode(requestDto.getPassword()) :
				user.getPassword();

		user.update(requestDto.getEmail(), password, requestDto.getUsername());
	}

	public void deleteUser(Long userId) {
		userRepository.findById(userId).get().markAsDeleted();

	}

}
