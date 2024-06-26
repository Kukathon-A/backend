package org.example.kukathonbackend.global.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.kukathonbackend.global.code.BaseCode;
import org.example.kukathonbackend.global.dto.ReasonDto;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum SuccessStatus implements BaseCode {
    _OK(HttpStatus.OK, "200", "성공 입니다."),
    _CREATED(HttpStatus.CREATED, "201", "성공적으로 생성되었습니다.");

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ReasonDto getReason() {
        return ReasonDto.builder()
                .isSuccess(true)
                .code(code)
                .message(message)
                .build();
    }

    @Override
    public ReasonDto getReasonHttpStatus() {
        return ReasonDto.builder()
                .isSuccess(true)
                .httpStatus(httpStatus)
                .code(code)
                .message(message)
                .build();
    }
}
