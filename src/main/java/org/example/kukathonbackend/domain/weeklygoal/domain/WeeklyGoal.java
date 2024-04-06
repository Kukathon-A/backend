package org.example.kukathonbackend.domain.weeklygoal.domain;


import jakarta.persistence.*;
import lombok.*;
import org.example.kukathonbackend.domain.week.domain.Week;

import java.time.LocalDate;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Data
@Table(name = "weekly_goal")
public class WeeklyGoal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "weekly_goal_id")
    private Long id;

    @Column(name = "week_fk_id")
    private Long weekId;

    @Column(name = "goal", columnDefinition = "TEXT")
    private String goal;

    @Column(name = "goal_success")
    private Boolean goalSuccess;
}
