package org.example.kukathonbackend.domain.user.application;

import org.example.kukathonbackend.domain.user.domain.User;

public interface UserService {
    void save(User user);
    User getUserByUserId(Long userId);
    User getUserByProviderId(String providerId);
}
