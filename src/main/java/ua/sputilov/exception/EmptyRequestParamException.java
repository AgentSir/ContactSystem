package ua.sputilov.exception;

public class EmptyRequestParamException extends RuntimeException  {

    public EmptyRequestParamException(String message) {
        super(message);
    }
}
