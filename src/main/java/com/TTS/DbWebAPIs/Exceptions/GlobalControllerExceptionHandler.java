package com.TTS.DbWebAPIs.Exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerExceptionHandler {


    @ExceptionHandler(UserNotFoundException.class)
    ResponseEntity<?> handleUserNotFound(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(InvalidLoginDetailsException.class)
    ResponseEntity<?> handleInvalidLoginDetailsException(Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(UserAlreadyExistsException.class)
    ResponseEntity<?> handleUserAlreadyExistsException(Exception e){
        return  new ResponseEntity<>(e.getMessage(),HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InternalServerException.class)
    ResponseEntity<?> handleInternalServerException(Exception e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DatabaseException.class)
    ResponseEntity<?> handleDatabaseException(Exception e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidAssignTaskRequestException.class)
    ResponseEntity<?> handleInvalidAssignTaskRequestException(Exception e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }
}
