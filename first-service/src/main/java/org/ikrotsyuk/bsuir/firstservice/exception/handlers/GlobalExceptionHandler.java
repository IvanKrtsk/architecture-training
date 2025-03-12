package org.ikrotsyuk.bsuir.firstservice.exception.handlers;

import org.ikrotsyuk.bsuir.firstservice.controller.ArticleController;
import org.ikrotsyuk.bsuir.firstservice.controller.ReactionController;
import org.ikrotsyuk.bsuir.firstservice.controller.WriterController;
import org.ikrotsyuk.bsuir.firstservice.exception.exceptions.ArticleWithSameTitleFoundException;
import org.ikrotsyuk.bsuir.firstservice.exception.exceptions.NoArticleWithReactionArticleIdFound;
import org.ikrotsyuk.bsuir.firstservice.exception.exceptions.NoWriterWithArticleWriterIdFound;
import org.ikrotsyuk.bsuir.firstservice.exception.exceptions.UserWithSameLoginFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice(assignableTypes = {ArticleController.class, WriterController.class, ReactionController.class})
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(UserWithSameLoginFoundException.class)
    public ResponseEntity<String> handleUserWithSameLoginFoundException(UserWithSameLoginFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(ArticleWithSameTitleFoundException.class)
    public ResponseEntity<String> handleArticleWithSameTitleFoundException(ArticleWithSameTitleFoundException ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(NoWriterWithArticleWriterIdFound.class)
    public ResponseEntity<String> handleNoWriterWithArticleWriterIdFound(NoWriterWithArticleWriterIdFound ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(NoArticleWithReactionArticleIdFound.class)
    public ResponseEntity<String> handleNoArticleWithReactionArticleIdFound(NoArticleWithReactionArticleIdFound ex){
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.FORBIDDEN);
    }
}
