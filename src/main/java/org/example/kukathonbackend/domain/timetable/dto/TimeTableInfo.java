package org.example.kukathonbackend.domain.timetable.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TimeTableInfo {
    String startWorkTime;
    String startLunchTime;
    String endLunchTime;
    String endWorkTime;
}
