package com.kadukov.spring.project.spring_project.exception_handling;

public class NoSuchTaskException extends RuntimeException{
    public NoSuchTaskException(String message) {
        super(message);
    }
}
