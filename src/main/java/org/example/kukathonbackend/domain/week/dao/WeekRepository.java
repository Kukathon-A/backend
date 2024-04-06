package org.example.kukathonbackend.domain.week.dao;

import org.example.kukathonbackend.domain.week.domain.Week;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WeekRepository extends JpaRepository<Week, Long> {

}