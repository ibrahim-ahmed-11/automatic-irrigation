package com.misrbanque.task.exceptions;

import com.misrbanque.task.models.Response;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
@Log4j2
public class ControllersGlobalExceptionHandler {

    @ExceptionHandler
    public final ResponseEntity<?> handleException(Exception ex, WebRequest request) {

        return new ResponseEntity<>(Response.fail(ex.getMessage()), HttpStatus.OK);
    }

}
