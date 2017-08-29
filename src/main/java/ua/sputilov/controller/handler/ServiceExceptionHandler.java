package ua.sputilov.controller.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ua.sputilov.entity.ContactApiError;
import ua.sputilov.exception.EmptyRequestParamException;

@RestControllerAdvice
public class ServiceExceptionHandler extends ResponseEntityExceptionHandler {

    private Logger LOG = LoggerFactory.getLogger(this.getClass());

    // 500
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({ Throwable.class })
    public ResponseEntity<Object> handleAll(final Exception ex, final WebRequest request) {
        LOG.error("Error: " + ex.getMessage(), ex);

        final ContactApiError apiError = new ContactApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage(), "error occurred");
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    // 400
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EmptyRequestParamException.class)
    public ResponseEntity<Object> handleControllerEmptyRequestParamException(EmptyRequestParamException ex) {
        LOG.error("RestException: " + ex.getMessage(), ex);
        final String error = ex.getMessage();
        final ContactApiError apiError = new ContactApiError(HttpStatus.BAD_REQUEST, ex.getMessage(), error);

        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }

    // 405
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(final HttpRequestMethodNotSupportedException ex, final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
        LOG.error("RestException: " + ex.getMessage(), ex);

        final StringBuilder builder = new StringBuilder();
        builder.append(ex.getMethod());
        builder.append(" method is not supported for this request. Supported methods are ");
        ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));

        final ContactApiError apiError = new ContactApiError(HttpStatus.METHOD_NOT_ALLOWED, ex.getMessage(), builder.toString());
        return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    }
}

