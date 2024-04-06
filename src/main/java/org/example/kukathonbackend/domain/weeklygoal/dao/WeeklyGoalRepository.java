package org.example.kukathonbackend.domain.weeklygoal.dao;

import org.example.kukathonbackend.domain.weeklygoal.domain.WeeklyGoal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeeklyGoalRepository extends JpaRepository<WeeklyGoal, Long> {

}