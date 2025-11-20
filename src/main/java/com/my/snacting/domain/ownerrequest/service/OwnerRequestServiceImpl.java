package com.my.snacting.domain.ownerrequest.service;

import com.my.snacting.domain.order.entity.Order;
import com.my.snacting.domain.order.repository.OrderRepository;
import com.my.snacting.domain.ownerrequest.dto.OwnerRequestGetResponse;
import com.my.snacting.domain.ownerrequest.entity.OwnerRequest;
import com.my.snacting.domain.ownerrequest.repository.OwnerRequestRepository;
import com.my.snacting.domain.ownerrequestlike.entity.OwnerRequestLike;
import com.my.snacting.domain.ownerrequestlike.repository.OwnerRequestLikeRepository;
import com.my.snacting.domain.user.entity.User;
import com.my.snacting.domain.user.repository.UserRepository;
import com.my.snacting.global.exception.BusinessException;
import com.my.snacting.global.exception.errorCode.OrderErrorCode;
import com.my.snacting.global.exception.errorCode.OwnerRequestErrorCode;
import com.my.snacting.global.exception.errorCode.UserErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OwnerRequestServiceImpl implements OwnerRequestService {

    private final OwnerRequestRepository ownerRequestRepository;
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final OwnerRequestLikeRepository ownerRequestLikeRepository;

    @Override
    @Transactional(readOnly = true)
    public List<OwnerRequestGetResponse> getOwnerRequestsByUserBudget(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(UserErrorCode.USER_NOT_FOUND));

        Order order = orderRepository.findByUser(user)
                .orElseThrow(() -> new BusinessException(OrderErrorCode.ORDER_NOT_FOUND));

        int budgetPerPerson = order.getBudgetPerPerson();

        List<OwnerRequest> ownerRequests = ownerRequestRepository.findAll();

        return ownerRequests.stream()
                .filter(ownerRequest -> ownerRequest.getPricePerPerson() <= budgetPerPerson)
                .map(ownerRequest -> new OwnerRequestGetResponse(
                        ownerRequest.getId(),
                        ownerRequest.getOrder() != null ? ownerRequest.getOrder().getId() : null,
                        ownerRequest.getStoreLocation(),
                        ownerRequest.getProductName(),
                        ownerRequest.getPricePerPerson()
                ))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public OwnerRequestGetResponse getOwnerRequestById(Long ownerRequestId) {
        OwnerRequest ownerRequest = ownerRequestRepository.findById(ownerRequestId)
                .orElseThrow(() -> new BusinessException(OwnerRequestErrorCode.OWNER_REQUEST_NOT_FOUND));

        return new OwnerRequestGetResponse(
                ownerRequest.getId(),
                ownerRequest.getOrder() != null ? ownerRequest.getOrder().getId() : null,
                ownerRequest.getStoreLocation(),
                ownerRequest.getProductName(),
                ownerRequest.getPricePerPerson()
        );
    }

    @Override
    @Transactional
    public void toggleOwnerRequestLike(Long userId, Long ownerRequestId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(UserErrorCode.USER_NOT_FOUND));

        OwnerRequest ownerRequest = ownerRequestRepository.findById(ownerRequestId)
                .orElseThrow(() -> new BusinessException(OwnerRequestErrorCode.OWNER_REQUEST_NOT_FOUND));

        Optional<OwnerRequestLike> existingLike = ownerRequestLikeRepository.findByUserAndOwnerRequest(user, ownerRequest);

        if (existingLike.isPresent()) {
            // 이미 좋아요한 경우 -> 좋아요 취소
            ownerRequestLikeRepository.delete(existingLike.get());
        } else {
            // 좋아요하지 않은 경우 -> 좋아요 추가
            OwnerRequestLike ownerRequestLike = OwnerRequestLike.builder()
                    .user(user)
                    .ownerRequest(ownerRequest)
                    .build();
            ownerRequestLikeRepository.save(ownerRequestLike);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<OwnerRequestGetResponse> getLikedOwnerRequests(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new BusinessException(UserErrorCode.USER_NOT_FOUND));

        List<OwnerRequestLike> ownerRequestLikes = ownerRequestLikeRepository.findByUser(user);

        return ownerRequestLikes.stream()
                .map(ownerRequestLike -> {
                    OwnerRequest ownerRequest = ownerRequestLike.getOwnerRequest();
                    return new OwnerRequestGetResponse(
                            ownerRequest.getId(),
                            ownerRequest.getOrder() != null ? ownerRequest.getOrder().getId() : null,
                            ownerRequest.getStoreLocation(),
                            ownerRequest.getProductName(),
                            ownerRequest.getPricePerPerson()
                    );
                })
                .collect(Collectors.toList());
    }
}
