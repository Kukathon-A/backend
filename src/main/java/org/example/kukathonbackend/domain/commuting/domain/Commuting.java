package org.example.kukathonbackend.domain.commuting.domain;


import jakarta.persistence.*;
import lombok.*;

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

    @Column(name = "week_fk_id")
    private Long weekId;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "commuting_time")
    private Integer commutingTime;
}
