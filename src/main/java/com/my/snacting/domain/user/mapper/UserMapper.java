package com.my.snacting.domain.user.mapper;

import com.my.snacting.domain.user.dto.request.UserCreateRequest;
import com.my.snacting.domain.user.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper {

    public static User toEntity(UserCreateRequest request) {
        return User.builder()
                .nickname(request.nickname())
                .school(request.school())
                .build();
    }
}
