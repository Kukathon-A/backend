package org.example.kukathonbackend.domain.week.application;

import lombok.RequiredArgsConstructor;
import org.example.kukathonbackend.domain.user.dao.UserRepository;
import org.example.kukathonbackend.domain.user.domain.Users;
import org.example.kukathonbackend.domain.week.dao.WeekRepository;
import org.example.kukathonbackend.domain.week.domain.Week;
import org.example.kukathonbackend.domain.week.dto.request.RequestWeeklyGoalDTO;
import org.example.kukathonbackend.domain.weeklygoal.dao.WeeklyGoalRepository;
import org.example.kukathonbackend.domain.weeklygoal.domain.WeeklyGoal;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.WeekFields;
import java.util.Locale;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class WeekService {

    private final WeekRepository weekRepository;
    private final UserRepository userRepository;
    private final WeeklyGoalRepository weeklyGoalRepository;

    public void createWeekGoal(RequestWeeklyGoalDTO requestWeeklyGoalDTO) {

        LocalDate currentDate = LocalDate.now();
        WeekFields weekFields = WeekFields.of(Locale.KOREA);
        int currentDayOfWeek = currentDate.get(ChronoField.DAY_OF_WEEK);
        int daysToAddToStart = 1 - currentDayOfWeek; // 1: Monday, 7: Sunday

        LocalDate startDay = currentDate.plusDays(daysToAddToStart);
        LocalDate endDay = startDay.plusDays(6); // 6 days after Monday is Sunday

        System.out.println("Start day: " + startDay);
        System.out.println("End day: " + endDay);

        Optional<Users> user = userRepository.findById(Long.valueOf(1));

        System.out.println(user.get());

        Week week = weekRepository.save(
                Week.builder()
                        .user(user.get())
                        .startDay(startDay)
                        .endDay(endDay)
                        .build()
        );

        for(String goal : requestWeeklyGoalDTO.getGoal()){
            weeklyGoalRepository.save(
                    WeeklyGoal.builder()
                            .week(week)
                            .goal(goal)
                            .goalSuccess(Boolean.FALSE)
                            .build()
            );
        }
    }
}
