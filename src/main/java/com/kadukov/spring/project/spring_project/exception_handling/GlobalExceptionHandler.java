package com.kadukov.spring.project.spring_project.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(NoSuchUserException noSuchUserException){
        IncorrectData incorrectData = new IncorrectData();
        incorrectData.setInfo(noSuchUserException.getMessage());

        return new ResponseEntity<>(incorrectData, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(NoSuchTaskException noSuchTaskException){
        IncorrectData incorrectData = new IncorrectData();
        incorrectData.setInfo(noSuchTaskException.getMessage());

        return new ResponseEntity<>(incorrectData, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleResourceNotFoundException(NoResourceFoundException ex, Model model) {
        model.addAttribute("status", 404);
        model.addAttribute("errorMessage", "Нет такой страницы");
        return "error";
    }

    @ExceptionHandler
    public ResponseEntity<IncorrectData> handleException(Exception exception){
        IncorrectData incorrectData = new IncorrectData();
        incorrectData.setInfo(exception.getMessage());

        return new ResponseEntity<>(incorrectData, HttpStatus.BAD_REQUEST);
    }

}
