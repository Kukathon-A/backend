package org.example.kukathonbackend.domain.tasklist.domain;


import jakarta.persistence.*;
import lombok.*;
import org.example.kukathonbackend.domain.commuting.domain.Commuting;
import org.example.kukathonbackend.domain.week.domain.Week;

import java.time.LocalDate;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Data
@Table(name = "task_list")
public class TaskList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long id;

    @Column(name = "task")
    private String task;


    @Column(name = "tag")
    private TagEnum tag;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "commuting_id")
    private Commuting commuting;

}
