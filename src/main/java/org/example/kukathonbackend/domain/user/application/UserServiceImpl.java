package org.example.kukathonbackend.domain.user.application;

import lombok.RequiredArgsConstructor;
import org.example.kukathonbackend.domain.user.dao.UserRepository;
import org.example.kukathonbackend.domain.user.domain.User;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public User getUserByUserId(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User getUserByProviderId(String providerId) {
        return userRepository.findByProviderId(providerId);
    }
}
