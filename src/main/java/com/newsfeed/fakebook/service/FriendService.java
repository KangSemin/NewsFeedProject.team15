package com.newsfeed.fakebook.service;

import com.newsfeed.fakebook.domain.Friend;
import com.newsfeed.fakebook.domain.User;
import com.newsfeed.fakebook.dto.userDto.UserResponseDto;
import com.newsfeed.fakebook.repository.FriendRepository;
import com.newsfeed.fakebook.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class FriendService {
    
    private final FriendRepository friendRepository;
    private final UserRepository userRepository;
    
    public void sendFriendRequest(Long fromUserId, Long toUserId) {
        if (fromUserId.equals(toUserId)) {
            throw new IllegalArgumentException("자기 자신에게 친구 요청을 보낼 수 없습니다.");
        }
        
        User fromUser = userRepository.findById(fromUserId)
                .orElseThrow(() -> new IllegalArgumentException("요청을 보내는 사용자를 찾을 수 없습니다."));
        User toUser = userRepository.findById(toUserId)
                .orElseThrow(() -> new IllegalArgumentException("요청을 받는 사용자를 찾을 수 없습니다."));
        
        if (friendRepository.findByFromUserAndToUser(fromUser, toUser).isPresent()) {
            throw new IllegalStateException("이미 친구 요청을 보냈습니다.");
        }
        
        var existingRequest = friendRepository.findByFromUserAndToUser(toUser, fromUser);


        if (existingRequest.isPresent()) {

            if(existingRequest.get().getStatus()== Friend.FriendStatus.ACCEPTED)
            {
                throw new IllegalStateException("이미 친구 입니다.");
            }
            existingRequest.get().accept();
            return;
        }
        
        Friend friend = new Friend(fromUser, toUser);
        friendRepository.save(friend);
    }
    
    public void acceptFriendRequest(Long fromUserId, Long toUserId) {
        User fromUser = userRepository.findById(fromUserId)
                .orElseThrow(() -> new IllegalArgumentException("요청을 보낸 사용자를 찾을 수 없습니다."));
        User toUser = userRepository.findById(toUserId)
                .orElseThrow(() -> new IllegalArgumentException("요청을 받은 사용자를 찾을 수 없습니다."));
        
        Friend friend = friendRepository.findByFromUserAndToUser(fromUser, toUser)
                .orElseThrow(() -> new IllegalArgumentException("친구 요청을 찾을 수 없습니다."));
        
        if (!friend.isRequestReceiver(toUserId)) {
            throw new IllegalStateException("친구 요청을 수락할 권한이 없습니다.");
        }
        
        friend.accept();
    }
    
    public void rejectFriendRequest(Long fromUserId, Long toUserId) {
        User fromUser = userRepository.findById(fromUserId)
                .orElseThrow(() -> new IllegalArgumentException("요청을 보낸 사용자를 찾을 수 없습니다."));
        User toUser = userRepository.findById(toUserId)
                .orElseThrow(() -> new IllegalArgumentException("요청을 받은 사용자를 찾을 수 없습니다."));
        
        Friend friend = friendRepository.findByFromUserAndToUser(fromUser, toUser)
                .orElseThrow(() -> new IllegalArgumentException("친구 요청을 찾을 수 없습니다."));
        
        if (!friend.isRequestReceiver(toUserId)) {
            throw new IllegalStateException("친구 요청을 거절할 권한이 없습니다.");
        }
        
        friend.reject();
    }
    
    public List<Friend> getAllFriends(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));
        return friendRepository.findByUserAndStatus(user, Friend.FriendStatus.ACCEPTED);
    }
}