package org.example.kukathonbackend.domain.route.application;

import org.example.kukathonbackend.domain.route.dto.request.UserRouteRequest;
import org.example.kukathonbackend.domain.route.dto.response.UserCoordinateResponse;
import org.example.kukathonbackend.domain.route.dto.response.UserRouteResponse;

import java.io.IOException;

public interface RouteService {
    UserRouteResponse editUserLocation(String authorizationHeader, UserRouteRequest userRouteRequest);
    UserCoordinateResponse ConvertUserLocation(UserRouteRequest userRouteRequest) throws IOException;
    int getCommutingTime(UserCoordinateResponse userCoordinateResponse);
}