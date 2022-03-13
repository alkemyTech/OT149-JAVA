package com.alkemy.ong.exception;

import com.alkemy.ong.dto.UserNotFoundErrorDTO;
import com.alkemy.ong.enumeration.ApplicationErrorCode;
import com.alkemy.ong.enumeration.Location;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Arrays;

@ControllerAdvice
public class GlobalControllerExceptionHandler extends AbstractExceptionHandler {

    /**
     * Este metodo se encarga de la captura del error USER_NOT_FOUND
     *
     * @return Devuelve la excepcion que estoy capturando y el dto
     */
    @ExceptionHandler(value = {UserNotFoundException.class})
    protected ResponseEntity<Object> handleUserNotFound(RuntimeException ex, WebRequest request) {

        UserNotFoundErrorDTO errorDTO = new UserNotFoundErrorDTO(
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                Arrays.asList("User not found")
        );

        return super.handleExceptionInternal(ex, new HttpHeaders(), HttpStatus.NOT_FOUND, request, ApplicationErrorCode.NOT_FOUND, errorDTO.getMessage());
    }
  
/*@ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exc){
    
        ErrorResponse error = new ErrorResponse();
        
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setMessage(exc.getFieldError().getDefaultMessage());
        error.setTimeStamp(ZonedDateTime.now());
        
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }*/

  /*@ExceptionHandler(value = {NotFoundException.class})
    protected ResponseEntity<Object> handleOrganizationNotFound(RuntimeException ex, WebRequest request){

        ErrorResponse error = new ErrorResponse();

        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setMessage(ex.getMessage());
        error.setTimeStamp(ZonedDateTime.now());

        return handleExceptionInternal(ex, error, new HttpHeaders(), HttpStatus.NOT_FOUND, request);

    }
    }*/

    @ExceptionHandler(value = {TestimonialNotFoundException.class})
    protected ResponseEntity<ErrorDetails> handleTestimonialNotFoundException(TestimonialNotFoundException exc) {

        ErrorDetails error = ErrorDetails.builder()
                .code(ApplicationErrorCode.NOT_FOUND)
                .description(exc.getMessage())
                .field("id")
                .location(Location.PATH)
                .build();

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}