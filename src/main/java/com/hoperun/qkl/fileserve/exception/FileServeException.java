package com.hoperun.qkl.fileserve.exception;

/**
 * 自定义文件的异常
 */
public class FileServeException extends RuntimeException {

    private static final long serialVersionUID = 2897790281352112625L;

    private String errorCode = null;

    public FileServeException(String errorMsg){
         super(errorMsg);
         this.errorCode = "";
    }

    public FileServeException(String errorCode,String errorMsg){
        super(errorMsg);
        this.errorCode = errorCode;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getLocalizeMessage() {
        return getMessage();
    }

}
