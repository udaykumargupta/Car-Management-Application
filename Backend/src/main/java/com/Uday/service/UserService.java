package com.Uday.service;

import com.Uday.model.User;

public interface UserService {
    public User findUserProfileByJwt(String jwt) throws Exception;

    public User findUserByEmail(String email) throws Exception;

    public User findUserById(Long userId) throws Exception;
}
