package org.example.kukathonbackend.global.auth;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.kukathonbackend.domain.user.application.UserService;
import org.example.kukathonbackend.domain.user.domain.Users;
import org.example.kukathonbackend.domain.user.domain.Users;
import org.example.kukathonbackend.global.util.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class OAuthLoginSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    @Value("${jwt.redirect}")
    private String REDIRECT_URI; // 프론트엔드로 Jwt 토큰을 리다이렉트할 URI

    @Value("${jwt.expiration-time}")
    private long TOKEN_EXPIRATION_TIME; // 토큰 유효기간

    private final JwtUtil jwtUtil;
    private final UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication; // 토큰
        final String provider = token.getAuthorizedClientRegistrationId();
        final LocalDateTime accessedTime = LocalDateTime.now(); // 현재 접속 시간
        String name = "";
        String providerId = "";
        Long userId;

        // 구글 || 카카오 || 네이버 로그인 요청
        // Access Token에서 provider id, name을 추출함
        if ("google".equals(provider)) {
            log.info("구글 로그인 요청");

            // 사용자 정보를 담고 있는 Map 추출
            Map<String, Object> userAttributes = token.getPrincipal().getAttributes();

            // provider id와 이름을 추출
            providerId = userAttributes.get("sub").toString();
            name = userAttributes.get("name").toString();
        } else if ("kakao".equals(provider)) {
            log.info("카카오 로그인 요청");

            // 사용자 정보를 담고 있는 Map 추출
            Map<String, Object> userAttributes = token.getPrincipal().getAttributes();

            // provider id와 이름을 추출
            providerId = userAttributes.get("id").toString();
            name = ((Map<?, ?>) userAttributes.get("properties")).get("nickname").toString();
        } else if ("naver".equals(provider)) {
            log.info("네이버 로그인 요청");

            // 사용자 정보를 담고 있는 Map 추출
            Map<String, Object> userAttributes = (Map<String, Object>) token.getPrincipal().getAttributes().get("response");

            // provider id와 이름을 추출
            providerId = userAttributes.get("id").toString();
            name = userAttributes.get("name").toString();
        }

        Users existUser = userService.getUserByProviderId(providerId); // provider id 기반으로 기존 or 신규 유저 파악

        if (existUser == null) {
            // 신규 유저인 경우
            log.info("신규 유저입니다. 등록을 진행합니다.");
            Users newUser = Users.builder()
                    .provider(provider)
                    .providerId(providerId)
                    .name(name)
                    .recentAccessedTime(accessedTime)
                    .build();

            // 신규 유저 정보 저장
            userService.save(newUser);
            userId = newUser.getId();
        } else {
            // 기존 유저인 경우
            log.info("기존 유저입니다. 업데이트를 진행합니다.");
            userId = existUser.getId();

            // 마지막 접속 시간 변경
            existUser.setRecentAccessedTime(accessedTime);

            // 기존 유저 정보 업데이트
            userService.save(existUser);
        }

        log.info("유저 ID : {}", userId);
        log.info("PROVIDER : {}", provider);
        log.info("PROVIDER_ID : {}", providerId);
        log.info("유저 이름 : {}", name);

        // Jwt 토큰 발급
        String jwtToken = jwtUtil.generateToken(userId, TOKEN_EXPIRATION_TIME);

        // Jwt 토큰을 담아 리다이렉트
        String redirectUri = String.format(REDIRECT_URI, jwtToken);
        getRedirectStrategy().sendRedirect(request, response, redirectUri);
    }
}
