package org.ikrotsyuk.bsuir.firstservice.exception.exceptions;

public class ArticleWithSameTitleFoundException extends RuntimeException {
    public ArticleWithSameTitleFoundException(String message) {
        super(message);
    }
}
