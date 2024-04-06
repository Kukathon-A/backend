package org.example.kukathonbackend.domain.route.dao;

import org.example.kukathonbackend.domain.route.domain.Route;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends JpaRepository<Route,Long> {
    Route findByUserId(Long userId);
}
