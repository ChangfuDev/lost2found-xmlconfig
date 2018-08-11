package cn.sevenleave.xmlconfig.utils.model;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author SevenLeave
 * @date 2018-07-29 14:33
 */
public class JsonResult implements Serializable {

    private static final long serialVersionUID = 4274648378649119118L;

    public static final String STATUS_SUCCESS = "success";
    public static final String STATUS_FAIL = "fail";

    private String status = STATUS_SUCCESS;
    private String errorCode = "";
    private String msg = null;
    private List<ErrorInfo> errors = new ArrayList<>();
    private Object data = null;

    public JsonResult() {
    }

    public static JsonResult success(String msg) {
        JsonResult success = new JsonResult();
        success.setStatus(STATUS_SUCCESS);
        success.setMsg(msg);
        return success;
    }

    public static JsonResult success(String msg, Object data) {
        JsonResult success = new JsonResult();
        success.setStatus(STATUS_SUCCESS);
        success.setMsg(msg);
        success.setData(data);
        return success;
    }

    public static JsonResult fail(String msg) {
        JsonResult fail = new JsonResult();
        fail.setStatus(STATUS_FAIL);
        fail.setMsg(msg);
        return fail;
    }

    public static JsonResult fail(String errorCode, String msg) {
        JsonResult fail = new JsonResult();
        fail.setStatus(STATUS_FAIL);
        fail.setErrorCode(errorCode);
        fail.setMsg(msg);
        return fail;
    }

    public static JsonResult fail(String errorCode, String msg, Object data) {
        JsonResult fail = new JsonResult();
        fail.setStatus(STATUS_FAIL);
        fail.setErrorCode(errorCode);
        fail.setMsg(msg);
        fail.setData(data);
        return fail;
    }

    public void addErrorInfo(ErrorInfo errorInfo) {
        this.errors.add(errorInfo);
    }

    /**
     * 描述：添加valid验证未通过的信息
     *
     * @param validatorErrors
     */
    public void addErrorInfo(List<ObjectError> validatorErrors) {
        Iterator<ObjectError> objectErrorIterator = validatorErrors.iterator();
        while (objectErrorIterator.hasNext()) {
            ObjectError objectError = objectErrorIterator.next();
            if (objectError instanceof FieldError) {
                FieldError fieldError = (FieldError) objectError;
                ErrorInfo errorInfo = new ErrorInfo();
                errorInfo.setField(fieldError.getField());
                errorInfo.setInfo(fieldError.getDefaultMessage());
                this.addErrorInfo(errorInfo);
            }
        }

    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<ErrorInfo> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorInfo> errors) {
        this.errors = errors;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
