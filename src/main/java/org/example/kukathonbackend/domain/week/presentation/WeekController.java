package org.example.kukathonbackend.domain.week.presentation;

import lombok.RequiredArgsConstructor;
import org.example.kukathonbackend.domain.week.application.WeekService;
import org.example.kukathonbackend.domain.week.dto.request.RequestWeeklyGoalDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/week")
@RequiredArgsConstructor
public class WeekController {

    private final WeekService weekService;

    @PostMapping("/goal")
    public ResponseEntity<String> receiveWeeklyGoals(@RequestBody RequestWeeklyGoalDTO requestWeeklyGoalDTO) {

        weekService.createWeekGoal(requestWeeklyGoalDTO);
        return ResponseEntity.ok("A~YO~");
    }
}
