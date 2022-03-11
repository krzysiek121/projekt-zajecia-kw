package pl.kurs.java.projektzajeciakw.exception;

import lombok.Builder;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity handleMethodArgumentNotValidException(MethodArgumentNotValidException exc) {
        return new ResponseEntity(
                exc.getFieldErrors().stream()
                        .map(fe -> ErrorDto.builder()
                                .field(fe.getField())
                                .message(fe.getDefaultMessage())
                                .build()).collect(Collectors.toList())
                , HttpStatus.BAD_REQUEST);
    }


    @Value
    @Builder
    static class ErrorDto {
        private String field;
        private String message;
    }
}
