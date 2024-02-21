package sysco.mini.project.users.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import sysco.mini.project.users.dto.ErrorResponse;

import java.util.stream.Collectors;

@ControllerAdvice
public class UserRestExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(UserRestExceptionHandler.class);


    // This exception handler will catch userNotFoundException throws
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(UserNotFoundException userNotFoundException) {
        logger.error("UserNotFoundException: {}", userNotFoundException.getMessage());

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        errorResponse.setMessage(userNotFoundException.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

    }


    // This exception handler will catch UserAlreadyExistsException throws
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(UserAlreadyExistsException userAlreadyExistsException) {
        logger.error("UserAlreadyExistsException: {}", userAlreadyExistsException.getMessage());

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.CONFLICT.value());
        errorResponse.setMessage(userAlreadyExistsException.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(errorResponse, HttpStatus.CONFLICT);

    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException methodArgumentNotValidException) {
        String errorMessage = methodArgumentNotValidException.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.joining(", "));

        logger.error("MethodArgumentNotValidException: {}", errorMessage);

        ErrorResponse errorReponse = new ErrorResponse();
        errorReponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorReponse.setMessage(errorMessage);
        errorReponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(errorReponse, HttpStatus.BAD_REQUEST);
    }

    // This exception handler will catch all other exceptions
    @ExceptionHandler
    public ResponseEntity<ErrorResponse> handleException(Exception exception) {
        logger.error("Exception: {}", exception.getMessage());

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        errorResponse.setMessage(exception.getMessage());
        errorResponse.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);

    }

}
