package com.triple.travel.common.exception;

import com.triple.travel.common.ResponseStatus;
import lombok.Builder;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

@Getter
public class ApiResponse<T> {
    private final ResponseStatus success;
    private final T data;
    private final String message;

    public ResponseStatus isSuccess() {
        return success;
    }

    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("success", success)
                .append("data", data)
                .append("message", message)
                .toString();
    }

    @Builder
    public ApiResponse(ResponseStatus success, T data, String message) {
        this.success = success;
        this.data = data;
        this.message = message;
    }
}
