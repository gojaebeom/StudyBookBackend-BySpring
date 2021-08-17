package me.studybook.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.naming.AuthenticationException;
import java.net.BindException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class ExceptionController {

    @ExceptionHandler(Exception.class)
    public ResponseEntity exceptionHandler(Exception e) {

        System.out.println(e.getMessage());

        Map<String, Object> responses = new HashMap<>();
        responses.put("message", e.getMessage());
        responses.put("status", 500);
        return ResponseEntity.internalServerError().body(responses);
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity bindExceptionHandler(BindException e) {
        Map<String, Object> responses = new HashMap<>();
        responses.put("message", e.getMessage());
        responses.put("status", 400);
        return ResponseEntity.badRequest().body(responses);
    }

    @ExceptionHandler(NoSuchFieldException.class)
    public ResponseEntity noSuchFieldExceptionHandler(NoSuchFieldException e) {
        Map<String, Object> responses = new HashMap<>();
        responses.put("message", e.getMessage());
        responses.put("status", 400);
        return ResponseEntity.badRequest().body(responses);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity authenticationException(AuthenticationException e) {
        Map<String, Object> responses = new HashMap<>();
        responses.put("message", e.getMessage());
        responses.put("status", 403);
        return ResponseEntity.badRequest().body(responses);
    }
}
