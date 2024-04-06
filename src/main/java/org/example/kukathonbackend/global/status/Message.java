package org.example.kukathonbackend.global.status;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Message {
    _EDIT_USER_LOCATION_MESSAGE("사용자의 출퇴근 경로를 변경합니다.");

    private final String message;
}