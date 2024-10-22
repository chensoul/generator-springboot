package <%= packageName %>.config;

import <%= packageName %>.exception.ResourceNotFoundException;
import java.net.URI;
import java.time.Instant;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    ProblemDetail onException(MethodArgumentNotValidException exception) {
        log.error("An unexpected error occurred", exception);

        ProblemDetail problemDetail =
                ProblemDetail.forStatusAndDetail(
                        HttpStatusCode.valueOf(400), "Invalid request content.");
        problemDetail.setTitle("Constraint Violation");
        List<ApiValidationError> validationErrorsList =
                exception.getAllErrors().stream()
                        .map(
                                objectError -> {
                                    FieldError fieldError = (FieldError) objectError;
                                    return new ApiValidationError(
                                            fieldError.getObjectName(),
                                            fieldError.getField(),
                                            fieldError.getRejectedValue(),
                                            Objects.requireNonNull(fieldError.getDefaultMessage(), ""));
                                })
                        .sorted(Comparator.comparing(ApiValidationError::field))
                        .toList();
        problemDetail.setProperty("violations", validationErrorsList);
        return problemDetail;
    }

    @ExceptionHandler(Exception.class)
    ProblemDetail onException(Exception exception) {
        log.error("An unexpected error occurred", exception);

        if (exception instanceof ResourceNotFoundException resourceNotFoundException) {
                ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
                        resourceNotFoundException.getHttpStatus(), resourceNotFoundException.getMessage());
                problemDetail.setTitle("Not Found");
                problemDetail.setType(URI.create("http://api.<%= appName %>.com/errors/not-found"));
                problemDetail.setProperty("errorCategory", "Generic");
                problemDetail.setProperty("timestamp", Instant.now());
                return problemDetail;
        } else {
            return ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(500), exception.getMessage());
        }
    }

    record ApiValidationError(String object, String field, Object rejectedValue, String message) {}

}
