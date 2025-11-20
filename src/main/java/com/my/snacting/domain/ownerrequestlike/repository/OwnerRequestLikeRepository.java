package com.my.snacting.domain.ownerrequestlike.repository;

import com.my.snacting.domain.ownerrequest.entity.OwnerRequest;
import com.my.snacting.domain.ownerrequestlike.entity.OwnerRequestLike;
import com.my.snacting.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface OwnerRequestLikeRepository extends JpaRepository<OwnerRequestLike, Long> {
    Optional<OwnerRequestLike> findByUserAndOwnerRequest(User user, OwnerRequest ownerRequest);
    List<OwnerRequestLike> findByUser(User user);
    boolean existsByUserAndOwnerRequest(User user, OwnerRequest ownerRequest);
}

