package com.devinhouse.DEVinPharmacy.exception;

import com.devinhouse.DEVinPharmacy.dto.ApiErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class RestApiExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleApiNotFoundException(NotFoundException exception){
        ApiErrorResponse error = new ApiErrorResponse(exception.getClass().getSimpleName(), exception.toString());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    };

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleApiBadRequestException(BadRequestException exception){
        ApiErrorResponse error = new ApiErrorResponse(exception.getClass().getSimpleName(), exception.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    };

    @ExceptionHandler(AlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleApiAlreadyRegisteredException(AlreadyExistsException exception){
        ApiErrorResponse error = new ApiErrorResponse(exception.getClass().getSimpleName(), exception.toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    };

    @Override
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, String> fieldErrors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            fieldErrors.put(fieldName, errorMessage);
        });
        ApiErrorResponse error = new ApiErrorResponse("ValidationException", "Invalid fields", fieldErrors);
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}
