package org.example.kukathonbackend.domain.user.application;

import org.example.kukathonbackend.domain.user.domain.Users;

public interface UserService {
    void save(Users users);
    Users getUserByUserId(Long userId);
    Users getUserByProviderId(String providerId);
}
