package org.example.kukathonbackend.domain.route.dto.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRouteRequest {
    private String startLocation;
    private String arriveLocation;
}