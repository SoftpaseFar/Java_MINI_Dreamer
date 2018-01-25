package cn.badguy.dream.exception;

public class BaseException extends RuntimeException {
    private final Integer errorCode = 10000;
    private final String description = "This is an exception";

    public Integer getErrorCode() {
        return errorCode;
    }

    public String getDescription() {
        return description;
    }
}
