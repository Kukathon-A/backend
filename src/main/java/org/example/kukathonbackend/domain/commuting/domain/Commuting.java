package org.example.kukathonbackend.domain.commuting.domain;


import jakarta.persistence.*;
import lombok.*;
import org.example.kukathonbackend.domain.user.domain.User;
import org.example.kukathonbackend.domain.week.domain.Week;

import java.time.LocalDate;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Data
@Table(name = "commuting")
public class Commuting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commuting_id")
    private Long id;

    @Column(name = "date")
    private LocalDate date ;


    @Column(name = "commuting_time")
    private Integer commutingTime;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "week_id")
    private Week week;

}
