package org.example.kukathonbackend.domain.route.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserCoordinateResponse {
    private String sx;
    private String sy;
    private String ex;
    private String ey;
}
