package cn.sevenleave.xmlconfig.support.exception;

/**
 * 描述：通用异常，运行时异常
 *
 * @author SevenLeave
 * @date 2018-08-06 16:34
 */
public class GenericException extends RuntimeException {
    
    private static final long serialVersionUID = 1299320094172782521L;
    
    private String errorCode;
    
    public GenericException(String message) {
        super(message);
    }
    
    public GenericException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
    }
    
    public GenericException(String message, Throwable cause, String errorCode) {
        super(message, cause);
        this.errorCode = errorCode;
    }
    
    public String getErrorCode() {
        return errorCode;
    }
    
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
}
