package com.newsfeed.fakebook.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity @Getter
@Table(name = "friends")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@IdClass(FriendId.class)
public class Friend {

    @Id
    @Column(name = "from_user_id")
    private Long fromUserId;
    
    @Id
    @Column(name = "to_user_id")
    private Long toUserId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_user_id", insertable = false, updatable = false)
    private User fromUser;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_user_id", insertable = false, updatable = false)
    private User toUser;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FriendStatus status = FriendStatus.PENDING;
    
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime requestDate;
    
    private LocalDateTime acceptedDate;

    @Builder
    public Friend(Long fromUserId, Long toUserId) {
        this.fromUserId = fromUserId;
        this.toUserId = toUserId;
    }
    
    public void accept() {
        this.status = FriendStatus.ACCEPTED;
        this.acceptedDate = LocalDateTime.now();
    }
    
    public void reject() {
        this.status = FriendStatus.REJECTED;
    }
    
    public boolean isRequestReceiver(Long userId) {
        return toUserId.equals(userId);
    }
    
    public boolean isRequester(Long userId) {
        return fromUserId.equals(userId);
    }
    
    public enum FriendStatus {
        PENDING, ACCEPTED, REJECTED
    }
}


