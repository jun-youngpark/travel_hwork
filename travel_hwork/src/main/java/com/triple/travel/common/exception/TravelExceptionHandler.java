package com.triple.travel.common.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import static com.triple.travel.common.ControllerHandler.error;


@Slf4j
@ControllerAdvice
@ResponseBody
@Order(Ordered.HIGHEST_PRECEDENCE)
public class TravelExceptionHandler {

    @ExceptionHandler({
            IllegalArgumentException.class,
            IllegalStateException.class,
            MethodArgumentNotValidException.class
    })
    public ResponseEntity<?> handleBadRequestException(Exception e) {
        log.error("Bad request exception occurred: {}", e.getMessage(), e);
        if (e instanceof MethodArgumentNotValidException) {
            return error(
                    ((MethodArgumentNotValidException) e).getBindingResult().getAllErrors().get(0).getDefaultMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
        return error(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler({
            ApiException.class,
            NumberFormatException.class
    })
    public ResponseEntity<?> ApiException(ApiException e) {
        log.error(e.getMessage(), e);
        return error(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
