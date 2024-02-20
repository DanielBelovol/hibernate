package org.example.exception;

public class CustomException extends Exception{
    public CustomException() {
        super();
    }

    // Конструктор, принимающий сообщение об ошибке
    public CustomException(String message) {
        super(message);
    }
}
