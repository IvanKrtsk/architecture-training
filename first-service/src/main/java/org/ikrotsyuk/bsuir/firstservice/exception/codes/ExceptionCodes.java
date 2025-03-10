package org.ikrotsyuk.bsuir.firstservice.exception.codes;

public enum ExceptionCodes {
    ArticleWithSameTitle(40301),
    UserWithSameLogin(40302);

    private final int code;

    ExceptionCodes(int code){
        this.code = code;
    }

    public int getCode(){
        return code;
    }
}
