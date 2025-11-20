package com.my.snacting.domain.user.service;

import com.my.snacting.domain.user.dto.request.UserCreateRequest;
import com.my.snacting.domain.user.dto.response.UserCreateResponse;

public interface UserService {
    UserCreateResponse createUser(UserCreateRequest request);
}
