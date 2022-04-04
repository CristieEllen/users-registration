package br.com.project.register.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ServiceExceptionHandler {

    @ExceptionHandler(CompiledException.class)
    public ResponseEntity<StandardError> objectNotFound(CompiledException objNC, HttpServletRequest request){
        StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), objNC.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> validation(MethodArgumentNotValidException e, HttpServletRequest request){
        ValidationError err = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erro de validação!", System.currentTimeMillis());
        for(FieldError fieldError : e.getBindingResult().getFieldErrors()){
            err.addError(fieldError.getField(), fieldError.getDefaultMessage());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(ChooseMoreThanAllowedException.class)
    public ResponseEntity<StandardError> validationAddress(ChooseMoreThanAllowedException e, HttpServletRequest request){
        StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(),e.getMessage(), System.currentTimeMillis());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<StandardError> validationCustomerType(HttpMessageNotReadableException e, HttpServletRequest request){
        StandardError err = new StandardError(HttpStatus.BAD_REQUEST.value(), "Digite PF para Pessoa Física ou PJ para pessoa Juridica", System.currentTimeMillis());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

}
