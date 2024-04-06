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

    @Column(name = "start_location")
    private String startLocation;

    @Column(name = "arrive_location")
    private String arriveLocation;

    @Column(name = "start_latitude", precision = 10, scale = 8)
    private BigDecimal startLatitude;

    @Column(name = "start_longitude", precision = 10, scale = 8)
    private BigDecimal startLongitude;

    @Column(name = "arrive_latitude", precision = 10, scale = 8)
    private BigDecimal arriveLatitude;

    @Column(name = "arrive_longitude", precision = 10, scale = 8)
    private BigDecimal arriveLongitude;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;
}