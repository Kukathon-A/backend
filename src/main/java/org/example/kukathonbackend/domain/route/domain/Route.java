package org.example.kukathonbackend.domain.route.domain;

import jakarta.persistence.*;
import lombok.*;
import org.example.kukathonbackend.domain.user.domain.User;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Data
@Table(name = "commuting_route")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "route_id")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "start_location")
    private String startLocation;

    @Column(name = "arrive_location")
    private String arriveLocation;

    @Column(name = "start_latitude")
    private String startLatitude;

    @Column(name = "start_longitude")
    private String startLongitude;

    @Column(name = "arrive_latitude")
    private String arriveLatitude;

    @Column(name = "arrive_longitude")
    private String arriveLongitude;
}