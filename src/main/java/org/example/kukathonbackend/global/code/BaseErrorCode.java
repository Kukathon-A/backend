package org.example.kukathonbackend.global.code;

import org.example.kukathonbackend.global.dto.ErrorReasonDto;

public interface BaseErrorCode {
    public ErrorReasonDto getReason();

    public ErrorReasonDto getReasonHttpStatus();
}
