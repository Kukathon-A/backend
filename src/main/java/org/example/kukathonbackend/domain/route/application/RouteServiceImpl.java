package org.example.kukathonbackend.domain.route.application;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.kukathonbackend.domain.route.dao.RouteRepository;
import org.example.kukathonbackend.domain.route.domain.Route;
import org.example.kukathonbackend.domain.user.domain.Users;
import org.example.kukathonbackend.domain.route.dto.request.UserRouteRequest;
import org.example.kukathonbackend.domain.route.dto.response.UserRouteResponse;
import org.example.kukathonbackend.domain.route.dto.response.UserCoordinateResponse;
import org.example.kukathonbackend.domain.user.dao.UserRepository;
import org.example.kukathonbackend.global.util.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Slf4j
@Service
@RequiredArgsConstructor
public class RouteServiceImpl implements RouteService {
    @Value("${odsay.api.key}")
    private String apiKey;

    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;
    private final RouteRepository routeRepository;

    @Override
    public UserRouteResponse editUserLocation(String authorizationHeader, UserRouteRequest userRouteRequest) {
        // 토큰에서 유저 id 가져오기
        String jwtToken = jwtUtil.getTokenFromHeader(authorizationHeader);
        Long userId = jwtUtil.getUserIdFromToken(jwtToken);

        Users users = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Users not found"));

        UserCoordinateResponse userCoordinateResponse = ConvertUserLocation(userRouteRequest);

        int commutingTime = getCommutingTime(userCoordinateResponse);

        // 사용자의 출근 시간 업데이트
        users.setCommutingTime(commutingTime);
        userRepository.save(users);

        // 사용자의 출퇴근 경로 가져오기
        Route userRoute = routeRepository.findByUserId(userId);

        // 사용자의 출퇴근 경로 업데이트
        userRoute.setStartLocation(userRouteRequest.getStartLocation());
        userRoute.setArriveLocation(userRouteRequest.getArriveLocation());
        userRoute.setStartLatitude(userCoordinateResponse.getSx());
        userRoute.setStartLongitude(userCoordinateResponse.getSy());
        userRoute.setArriveLatitude(userCoordinateResponse.getEx());
        userRoute.setArriveLongitude(userCoordinateResponse.getEy());
        routeRepository.save(userRoute);

        return UserRouteResponse.builder()
                .startLocation(userRouteRequest.getStartLocation())
                .arriveLocation(userRouteRequest.getArriveLocation())
                .build();
    }

    @Override
    public UserCoordinateResponse ConvertUserLocation(UserRouteRequest userRouteRequest) {
        try {
            // 출발점 API 요청 URL
            String startUrlInfo = "https://api.odsay.com/v1/api/searchStation?lang=0&stationName=" + URLEncoder.encode(userRouteRequest.getStartLocation(), "UTF-8") + "&apiKey=" + URLEncoder.encode(apiKey, "UTF-8");

            // 도착점 API 요청 URL
            String arriveUrlInfo = "https://api.odsay.com/v1/api/searchStation?lang=0&stationName=" + URLEncoder.encode(userRouteRequest.getArriveLocation(), "UTF-8") + "&apiKey=" + URLEncoder.encode(apiKey, "UTF-8");

            // 출발점 요청
            URL startUrl = new URL(startUrlInfo);
            HttpURLConnection conn = (HttpURLConnection) startUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                response.append(line);
            }
            bufferedReader.close();
            conn.disconnect();

            // JSON 파싱 (출발점)
            JsonObject jsonStartResponse = JsonParser.parseString(response.toString()).getAsJsonObject();
            JsonArray startStationArray = jsonStartResponse.getAsJsonObject("result").getAsJsonArray("station");
            JsonObject firstStartStation = startStationArray.get(0).getAsJsonObject();
            String sx = firstStartStation.get("x").getAsString();
            String sy = firstStartStation.get("y").getAsString();

            // 도착점 요청
            URL arriveUrl = new URL(arriveUrlInfo);
            conn = (HttpURLConnection) arriveUrl.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");

            bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            response = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                response.append(line);
            }
            bufferedReader.close();
            conn.disconnect();

            // JSON 파싱 (도착점)
            JsonObject jsonArriveResponse = JsonParser.parseString(response.toString()).getAsJsonObject();
            JsonArray arriveStationArray = jsonArriveResponse.getAsJsonObject("result").getAsJsonArray("station");
            JsonObject firstArriveStation = arriveStationArray.get(0).getAsJsonObject();
            String ex = firstArriveStation.get("x").getAsString();
            String ey = firstArriveStation.get("y").getAsString();

            return UserCoordinateResponse.builder()
                    .sx(sx)
                    .sy(sy)
                    .ex(ex)
                    .ey(ey)
                    .build();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public int getCommutingTime(UserCoordinateResponse userCoordinateResponse) {
        try {
            String urlInfo = "https://api.odsay.com/v1/api/searchPubTransPathT?SX=" + userCoordinateResponse.getSx() +
                    "&SY=" + userCoordinateResponse.getSy() +
                    "&EX=" + userCoordinateResponse.getEx() +
                    "&EY=" + userCoordinateResponse.getEy() +
                    "&apiKey=" + URLEncoder.encode(apiKey, "UTF-8");

            // http 연결
            URL url = new URL(urlInfo);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-type", "application/json");

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sb.append(line);
            }
            bufferedReader.close();
            conn.disconnect();

            // JSON 파싱
            JsonObject jsonResponse = JsonParser.parseString(sb.toString()).getAsJsonObject();
            JsonArray pathArray = jsonResponse.getAsJsonObject("result").getAsJsonArray("path");
            JsonObject firstPath = pathArray.get(0).getAsJsonObject();

            // 사용자 출근 시간 파싱
            int commutingTime = firstPath.getAsJsonObject("info").get("totalTime").getAsInt();
            log.info(String.valueOf(commutingTime));

            return commutingTime;
        } catch (IOException e) {
            e.printStackTrace();
            return 0;
        }
    }
}