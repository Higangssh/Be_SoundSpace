package com.the.soundspace.user.service;


import com.the.soundspace.user.model.entities.UserEntity;
import jakarta.servlet.http.HttpServletResponse;

public interface UserService {
    public Long checkAndJoinUser(UserEntity user, HttpServletResponse response);
    public UserEntity createUser(String email);
}
