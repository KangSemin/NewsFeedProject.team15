package com.newsfeed.fakebook.service;

import com.newsfeed.fakebook.domain.Friend;
import com.newsfeed.fakebook.domain.User;
import com.newsfeed.fakebook.repository.FriendRepository;
import com.newsfeed.fakebook.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

        if(userRepository.findById(toUserId).isEmpty())
        {
            throw new IllegalArgumentException("존재하지 않는 유저입니다.");
        }

        Optional<Friend> existingRequest = friendRepository.findByUserIds(fromUserId, toUserId);

        if (existingRequest.isPresent()) {

            if(existingRequest.get().getStatus()== Friend.FriendStatus.ACCEPTED)
            {
                throw new IllegalArgumentException("이미 친구 입니다.");
            }
            if(existingRequest.get().getFromUserId().equals(fromUserId))
            {
                throw new IllegalArgumentException("이미 보낸 요청입니다.");
            }

            existingRequest.get().accept();
            return;
        }
        
        Friend friend = Friend.builder()
                .fromUserId(fromUserId)
                .toUserId(toUserId)
                .build();

        friendRepository.save(friend);
    }
    
    public void acceptFriendRequest(Long fromUserId, Long toUserId) {

        Friend friend = friendRepository.findByFromUserIdAndToUserId(fromUserId, toUserId)
                .orElseThrow(() -> new IllegalArgumentException("친구 요청을 찾을 수 없습니다."));

        if(friend.getStatus() != Friend.FriendStatus.PENDING)
        {
            throw new IllegalArgumentException("친구 요청을 찾을 수 없습니다.");
        }

        if (!friend.isRequestReceiver(toUserId)) {
            throw new IllegalStateException("친구 요청을 수락할 권한이 없습니다.");
        }
        friend.accept();
    }
    
    public void rejectFriendRequest(Long fromUserId, Long toUserId) {

        Friend friend = friendRepository.findByFromUserIdAndToUserId(fromUserId, toUserId)
                .orElseThrow(() -> new IllegalArgumentException("친구 요청을 찾을 수 없습니다."));

        if(friend.getStatus() != Friend.FriendStatus.PENDING)
        {
            throw new IllegalArgumentException("친구 요청을 찾을 수 없습니다.");
        }

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

    public void removeFriend(Long fromUserId, Long toUserId) {
        friendRepository.delete(friendRepository.findByUserIds(fromUserId, toUserId).orElseThrow());
    }
}