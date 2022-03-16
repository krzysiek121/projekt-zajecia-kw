package pl.kurs.java.projektzajeciakw.exception;

import lombok.Builder;
import lombok.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Arrays;
import java.util.stream.Collectors;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ErrorDto> handleMethodArgumentNotValidException(NullPointerException exc) {
        return new ResponseEntity<ErrorDto>(new ErrorDto("NO_COMPANY_FOUND"), HttpStatus.NOT_FOUND);
    }


    @Value
    @Builder
    static class ErrorDto {
        private String message;
    }
}
