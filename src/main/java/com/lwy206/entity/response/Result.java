package com.lwy206.entity.response;

import com.lwy206.constants.ResponseStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Result<T> {

    private String status;
    private String message;
    private T data;

    public static <T> Result<T> success() {
        return success(null);
    }

    public static <T> Result<T> success(T data) {
        return Result.<T>builder()
                .data(data)
                .message(ResponseStatus.SUCCESS.getMessage())
                .status(ResponseStatus.SUCCESS.getStatus())
                .build();
    }

    public static <T> Result<T> error() {
        return error(null);
    }

    public static <T> Result<T> error(T data) {
        return Result.<T>builder()
                .data(data)
                .message(ResponseStatus.ERROR.getMessage())
                .status(ResponseStatus.ERROR.getStatus())
                .build();
    }

    public static <T> Result<T> error(Integer status, String message) {
        return Result.<T>builder()
                .data(null)
                .message(message)
                .status(status.toString())
                .build();
    }
}
