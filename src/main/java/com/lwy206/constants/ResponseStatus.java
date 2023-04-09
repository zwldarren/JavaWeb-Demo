package com.lwy206.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseStatus {
    SUCCESS("200", "ok"),
    ERROR("500", "server error");

    private final String status;
    private final String message;
}
