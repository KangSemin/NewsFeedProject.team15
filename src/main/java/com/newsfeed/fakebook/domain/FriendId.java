package com.newsfeed.fakebook.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FriendId implements Serializable {
    
    private Long fromUserId;
    private Long toUserId;
    
    public FriendId(Long fromUserId, Long toUserId) {
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FriendId friendId = (FriendId) o;
        return Objects.equals(fromUserId, friendId.fromUserId) &&
               Objects.equals(toUserId, friendId.toUserId);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(fromUserId, toUserId);
    }
}