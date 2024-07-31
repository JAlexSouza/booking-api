package br.com.ntconsult.bookingapi.config.exception.advice;

import br.com.ntconsult.bookingapi.config.exception.custom.AccommodationUnavailableException;
import br.com.ntconsult.bookingapi.config.exception.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebExchange;

import java.time.LocalDateTime;


@RestControllerAdvice
public class ApiControllerAdvice {

    @ExceptionHandler(AccommodationUnavailableException.class)
    public ResponseEntity<ErrorResponse> accommodationUnavailableException(ServerWebExchange request, Exception ex) {
        return new ResponseEntity<ErrorResponse>(ErrorResponse.builder()
                .timestamp(LocalDateTime.now())
                .status(400)
                .error("Bad Request")
                .message(ex.getMessage())
                .path(request.getRequest().getPath().toString())
                .build(), HttpStatus.BAD_REQUEST);
    }
}
