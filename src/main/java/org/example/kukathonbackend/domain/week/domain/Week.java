package org.example.kukathonbackend.domain.week.domain;


import jakarta.persistence.*;
import lombok.*;
import org.example.kukathonbackend.domain.user.domain.Users;

import java.time.LocalDate;

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

    @Column(name = "start_day")
    private LocalDate startDay;

    @Column(name = "end_day")
    private LocalDate endDay;

    @Column(name = "goal")
    private String goal;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Users user;
}
