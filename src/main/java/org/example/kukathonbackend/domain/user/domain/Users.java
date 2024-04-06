package org.example.kukathonbackend.domain.user.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Data
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "provider")
    private String provider;

    @Column(name = "provider_id")
    private String providerId;

    @Column(name = "recent_accessed_time")
    private LocalDateTime recentAccessedTime;

    @Column(name = "commuting_time")
    private Integer commutingTime;

    @Column(name = "remain_commuting_time")
    private Integer remainCommutingTime;
}