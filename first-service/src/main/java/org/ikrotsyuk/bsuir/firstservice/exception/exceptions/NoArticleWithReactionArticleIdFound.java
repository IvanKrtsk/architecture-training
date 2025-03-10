package org.ikrotsyuk.bsuir.firstservice.exception.exceptions;

public class NoArticleWithReactionArticleIdFound extends RuntimeException {
    public NoArticleWithReactionArticleIdFound(String message) {
        super(message);
    }
}
