package com.my.snacting.domain.ownerrequestlike.entity;

import com.my.snacting.domain.ownerrequest.entity.OwnerRequest;
import com.my.snacting.domain.user.entity.User;
import com.my.snacting.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "owner_request_likes",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "owner_request_id"}))
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OwnerRequestLike extends BaseEntity {

    @Id
    @Column(name = "owner_request_like_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_request_id", nullable = false)
    private OwnerRequest ownerRequest;

    @Builder
    public OwnerRequestLike(User user, OwnerRequest ownerRequest) {
        this.user = user;
        this.ownerRequest = ownerRequest;
    }
}
