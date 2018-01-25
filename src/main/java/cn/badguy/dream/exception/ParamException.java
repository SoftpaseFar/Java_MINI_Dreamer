package cn.badguy.dream.exception;

public class ParamException extends BaseException {
    private final Integer errorCode;
    private final String description;

    public ParamException() {
        this.errorCode = super.getErrorCode();
        this.description = super.getDescription();
    }

    public ParamException(Integer errorCode) {
        this.errorCode = errorCode;
        this.description = super.getDescription();
    }

    public ParamException(String description) {
        this.errorCode = super.getErrorCode();
        this.description = description;
    }

    public ParamException(Integer errorCode, String description) {
        this.errorCode = errorCode;
        this.description = description;
    }

    @Override
    public Integer getErrorCode() {
        return errorCode;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
