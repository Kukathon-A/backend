package org.example.kukathonbackend.domain.week.domain;


import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Data
@Table(name = "week")
public class Week {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "week_id")
    private Long id;

    @Column(name = "user_fk_id")
    private String userId;

    @Column(name = "start_day")
    private String startDay;

    @Column(name = "end_day")
    private String endDay;

    @Column(name = "goal")
    private String goal;
}
