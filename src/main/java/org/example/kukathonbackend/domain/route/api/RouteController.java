package org.example.kukathonbackend.domain.route.api;

import lombok.RequiredArgsConstructor;
import org.example.kukathonbackend.domain.route.application.RouteService;
import org.example.kukathonbackend.domain.route.dto.request.UserRouteRequest;
import org.example.kukathonbackend.domain.route.dto.response.UserRouteResponse;
import org.example.kukathonbackend.global.ApiResponse;
import org.example.kukathonbackend.global.status.Message;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/route")
public class RouteController {
    private final RouteService routeService;

    // 사용자의 출발 or 도착지를 초기 입력하는 메서드
    @PostMapping
    public ApiResponse<UserRouteResponse> addUserLocation(@RequestHeader("Authorization") String authorizationHeader,
                                                           @RequestBody UserRouteRequest userRouteRequest) {

        UserRouteResponse userRouteResponse = routeService.editUserLocation(authorizationHeader, userRouteRequest);

        return ApiResponse.onSuccess(Message._EDIT_USER_LOCATION_MESSAGE.getMessage(), userRouteResponse);
    }
}
