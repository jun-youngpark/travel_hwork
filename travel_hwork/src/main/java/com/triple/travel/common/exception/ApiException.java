package com.triple.travel.common.exception;

import lombok.Getter;

@Getter
public class ApiException extends RuntimeException {
	private static final long serialVersionUID = -8569284490669670838L;
	private ErrorCode error;

    public ApiException() {
        super();
    }
    public ApiException(String msg) {
        super(msg);
    }
    public ApiException(String msg, Throwable t) {
        super(msg, t);
    }
    public ApiException(ErrorCode error) {
        super(error.getMessage());
        this.error = error;
    }
    public ApiException(ErrorCode error, Throwable t) {
        super(error.getMessage(), t);
        this.error = error;
    }
}
