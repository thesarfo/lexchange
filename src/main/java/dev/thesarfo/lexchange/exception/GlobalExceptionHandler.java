package dev.thesarfo.lexchange.exception;

import dev.thesarfo.lexchange.exception.user.UserAlreadyExistsException;
import dev.thesarfo.lexchange.model.error.ErrorDetails;
import dev.thesarfo.lexchange.util.ResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<Object> badRequestHandler(UserAlreadyExistsException ex, WebRequest req){
        List<ErrorDetails> errors = new ArrayList<>();
        errors.add(new ErrorDetails(ex.getMessage(), req.getDescription(false), LocalDateTime.now()));
        return ResponseHandler.errorResponse(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<Object> unauthorizedExceptionHandler(BadCredentialsException ex, WebRequest req) {
        List<ErrorDetails> errors = new ArrayList<>();
        errors.add(new ErrorDetails(ex.getMessage(), req.getDescription(false), LocalDateTime.now()));
        return ResponseHandler.errorResponse(errors, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        List<ErrorDetails> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            String errorMessage = error.getDefaultMessage();
            String field = error.getField();
            errors.add(new ErrorDetails(errorMessage, field, LocalDateTime.now()));
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
