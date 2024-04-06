package org.example.kukathonbackend.domain.timetable.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TimeTableInfo {
    LocalDateTime startWorkTime;
    LocalDateTime startLunchTime;
    LocalDateTime endLunchTime;
    LocalDateTime endWorkTime;
}
