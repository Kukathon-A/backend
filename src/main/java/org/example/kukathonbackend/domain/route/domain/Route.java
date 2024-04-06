package org.example.kukathonbackend.domain.route.domain;

import jakarta.persistence.*;
import lombok.*;
import org.example.kukathonbackend.domain.user.domain.Users;

import java.math.BigDecimal;

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

    @Column(name = "start_latitude")
    private String startLatitude;

    @Column(name = "start_longitude")
    private String startLongitude;

    @Column(name = "arrive_latitude")
    private String arriveLatitude;

    @Column(name = "arrive_longitude")
    private String arriveLongitude;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Users user;
}