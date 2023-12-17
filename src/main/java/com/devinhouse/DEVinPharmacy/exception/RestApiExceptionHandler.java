package com.devinhouse.DEVinPharmacy.exception;

import com.devinhouse.DEVinPharmacy.dto.ApiErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestApiExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ApiNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleApiNotFoundException(ApiNotFoundException exception){
        ApiErrorResponse error = new ApiErrorResponse(exception.getClass().getSimpleName(), exception.toString());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    };

    //FIXME: A mensagem de erro ainda está ruim. Consertar.
}
