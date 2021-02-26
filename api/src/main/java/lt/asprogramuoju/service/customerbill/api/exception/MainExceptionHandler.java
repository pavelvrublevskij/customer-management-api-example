package lt.asprogramuoju.service.customerbill.api.exception;

import lt.asprogramuoju.gen.customerbill.model.Error;
import lt.asprogramuoju.service.customerbill.core.exception.GeneralException;
import lt.asprogramuoju.service.customerbill.core.exception.WSException;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;

@RestControllerAdvice
public class MainExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<lt.asprogramuoju.gen.customerbill.model.Error> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
        lt.asprogramuoju.gen.customerbill.model.Error errorDetails =
                lt.asprogramuoju.gen.customerbill.model.Error.builder()
                        .status(HttpStatus.NOT_FOUND.toString())
                        .message(ex.getMessage())
                        .referenceError(URI.create(request.getDescription(false)))
                        .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(WSException.class)
    public ResponseEntity<lt.asprogramuoju.gen.customerbill.model.Error> handleBadRequest(WSException ex, WebRequest request) {
        lt.asprogramuoju.gen.customerbill.model.Error errorDetails =
                lt.asprogramuoju.gen.customerbill.model.Error.builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                        .code(ex.getErrorCode())
                        .reason(ex.getReason())
                        .message(ex.getCause().getMessage())
                        .referenceError(URI.create(request.getDescription(false)))
                        .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(GeneralException.class)
    public ResponseEntity<lt.asprogramuoju.gen.customerbill.model.Error> handleBadRequest(GeneralException ex, WebRequest request) {
        lt.asprogramuoju.gen.customerbill.model.Error errorDetails =
                lt.asprogramuoju.gen.customerbill.model.Error.builder()
                        .status(HttpStatus.BAD_REQUEST.toString())
                        .code(ex.getErrorCode())
                        .reason(ex.getReason())
                        .referenceError(URI.create(request.getDescription(false)))
                        .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<lt.asprogramuoju.gen.customerbill.model.Error> handleInternalServerError(RuntimeException ex, WebRequest request) {
        lt.asprogramuoju.gen.customerbill.model.Error errorDetails =
                Error.builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR.toString())
                        .code("0000") // all not catched errors
                        .reason("System error")
                        .message(ex.getMessage())
                        .referenceError(URI.create(request.getDescription(false)))
                        .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status, WebRequest request) {
        lt.asprogramuoju.gen.customerbill.model.Error errorDetails =
                lt.asprogramuoju.gen.customerbill.model.Error.builder()
                        .status(HttpStatus.BAD_REQUEST.toString())
                        .message(ex.getMessage())
                        .referenceError(URI.create(request.getDescription(false)))
                        .build();
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

}
