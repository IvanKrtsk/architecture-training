package org.ikrotsyuk.bsuir.firstservice.exception.exceptions;

public class NoWriterWithArticleWriterIdFound extends RuntimeException {
    public NoWriterWithArticleWriterIdFound(String message) {
        super(message);
    }
}
