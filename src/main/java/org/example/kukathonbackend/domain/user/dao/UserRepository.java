package org.example.kukathonbackend.domain.user.dao;

import org.example.kukathonbackend.domain.user.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {
    Users findByProviderId(String providerId);

}
