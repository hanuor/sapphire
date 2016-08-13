package com.hanuor.sapphire.utils;

/**
 * Created by Shantanu Johri on 29-07-2016.
 */
public class QuestException extends RuntimeException{
    private int httpErrorCode;
    private int appErrorCode;

    public QuestException() {
    }

    public QuestException(String detailMessage) {
        super(detailMessage);
    }

    public QuestException(Throwable throwable) {
        super(throwable);
    }

    public QuestException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public QuestException(String detailMessage, int httpErrorCode, int appErrorCode) {
        super(detailMessage);
        this.httpErrorCode = httpErrorCode;
        this.appErrorCode = appErrorCode;
    }

    public QuestException(int httpErrorCode, int appErrorCode) {
        this.httpErrorCode = httpErrorCode;
        this.appErrorCode = appErrorCode;
    }

    public void setHttpErrorCode(int httpErrorCode) {
        this.httpErrorCode = httpErrorCode;
    }

    public int getHttpErrorCode() {
        return this.httpErrorCode;
    }

    public void setAppErrorCode(int appErrorCode) {
        this.appErrorCode = appErrorCode;
    }

    public int getAppErrorCode() {
        return this.appErrorCode;
    }

}
