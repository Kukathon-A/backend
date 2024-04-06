package org.example.kukathonbackend.domain.user.application;

import org.example.kukathonbackend.domain.user.domain.Users;
import org.example.kukathonbackend.domain.user.domain.Users;

public interface UserService {
    void save(Users user);
    Users getUserByUserId(Long userId);
    Users getUserByProviderId(String providerId);
}
