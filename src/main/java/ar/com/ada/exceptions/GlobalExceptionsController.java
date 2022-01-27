package ar.com.ada.exceptions;

import java.sql.Timestamp;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionsController {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Error> handleResourceNotFoundException(ResourceNotFoundException ex){
        Error error = new Error();

        error.setTimestamp(new Timestamp(System.currentTimeMillis()));
        error.setStatusCode(HttpStatus.NOT_FOUND.value());
        error.setMessagge(ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);

    }
    
}
