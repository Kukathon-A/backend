package org.example.kukathonbackend.global.code;

import org.example.kukathonbackend.global.dto.ReasonDto;

public interface BaseCode {
    public ReasonDto getReason();

    public ReasonDto getReasonHttpStatus();
}
