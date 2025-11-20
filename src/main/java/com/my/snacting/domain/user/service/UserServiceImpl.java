package com.my.snacting.domain.user.service;

import com.my.snacting.domain.user.dto.request.UserCreateRequest;
import com.my.snacting.domain.user.dto.response.UserCreateResponse;
import com.my.snacting.domain.user.entity.User;
import com.my.snacting.domain.user.mapper.UserMapper;
import com.my.snacting.domain.user.repository.UserRepository;
import com.my.snacting.global.exception.BusinessException;
import com.my.snacting.global.exception.errorCode.UserErrorCode;
import com.my.snacting.global.exception.errorCode.UserResponseCode;
import com.my.snacting.global.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    @Transactional
    public UserCreateResponse createUser(UserCreateRequest request) {
        if (request.nickname() == null || request.nickname().isBlank()) {
            throw new BusinessException(UserErrorCode.USER_NICKNAME_REQUIRED);
        }
        if (request.school() == null || request.school().isBlank()) {
            throw new BusinessException(UserErrorCode.USER_SCHOOL_REQUIRED);
        }

        User user = UserMapper.toEntity(request);
        User savedUser = userRepository.save(user);

        String accessToken = jwtTokenProvider.createToken(savedUser.getId());

        return new UserCreateResponse(
                savedUser.getId(),
                UserResponseCode.USER_CREATED.getMessage(),
                accessToken
        );
    }
}
