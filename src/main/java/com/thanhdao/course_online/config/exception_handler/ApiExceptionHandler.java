package com.thanhdao.course_online.config.exception_handler;

import com.thanhdao.course_online.utils.constants.ExceptionConstant;
import com.thanhdao.course_online.utils.exceptions.ApplicationException;
import org.springframework.http.*;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.security.SignatureException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
//@Configuration
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    public ProblemDetail handleIllegalArgumentException(
            ApplicationException ex
    ){
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(ex.getExceptionType().getHttpStatus(), "Illegal argument");
        problemDetail.setProperty("errors", ex.getDescriptions());
        return problemDetail;
    }

    private ProblemDetail createProblemDetail(String errorCode, Object errorMessage, String title) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, title);
        problemDetail.setTitle(title);
        problemDetail.setProperty("code",  errorCode);
        problemDetail.setProperty("errors", errorMessage);
        return problemDetail;
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        List<String> messages = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(x -> x.getField() + ": " + x.getDefaultMessage())
                .toList();
        Map<String, String> validationErrors = ex.getBindingResult().getFieldErrors()
                .stream().collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
        var problemDetail = createProblemDetail(ExceptionConstant.PropertyValidationErrorCode, validationErrors, ExceptionConstant.PropertyValidationErrorTitle);
        return super.handleExceptionInternal(ex, problemDetail, headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ProblemDetail  handleBadCredentialsException(
            BadCredentialsException ex
    ){
//        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, "Invalid credentials");
//        problemDetail.setProperty("errors", ex.getMessage());
        return createProblemDetail(ExceptionConstant.User.BadCredentials.code, ExceptionConstant.User.BadCredentials.message, ExceptionConstant.User.BadCredentials.title);
    }

//    @ExceptionHandler(SignatureException.class)
//    public ProblemDetail handleSignatureException(
//            SignatureException ex
//    ){
//        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.UNAUTHORIZED, "Invalid jwt");
//        var problemDetail =  createProblemDetail(ExceptionConstant.User.InvalidCredentials.code, ex.getMessage(), ExceptionConstant.User.InvalidCredentials.title);
//        problemDetail.setStatus(HttpStatus.UNAUTHORIZED);
//        problemDetail.setProperty("errors", ex.getMessage());
//
//        return problemDetail;
//    }
}
