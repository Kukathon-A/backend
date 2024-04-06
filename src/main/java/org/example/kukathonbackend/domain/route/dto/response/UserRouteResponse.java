package org.example.kukathonbackend.domain.route.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRouteResponse {
    private String startLocation;
    private String arriveLocation;
}