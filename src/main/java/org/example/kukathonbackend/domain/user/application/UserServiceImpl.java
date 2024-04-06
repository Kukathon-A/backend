package org.example.kukathonbackend.domain.user.application;

import lombok.RequiredArgsConstructor;
import org.example.kukathonbackend.domain.user.dao.UserRepository;
import org.example.kukathonbackend.domain.user.domain.Users;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public void save(Users users) {
        userRepository.save(users);
    }

    @Override
    public Users getUserByUserId(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Users not found"));
    }

    @Override
    public Users getUserByProviderId(String providerId) {
        return userRepository.findByProviderId(providerId);
    }
}
