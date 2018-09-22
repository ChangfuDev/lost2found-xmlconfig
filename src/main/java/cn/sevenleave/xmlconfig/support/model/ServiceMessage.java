package cn.sevenleave.xmlconfig.support.model;

import java.io.Serializable;

/**
 * 描述：用于Service的实现类给Controller返回操作的结果（成功还是失败）
 * 针对不返回对象或数组的Service的实现类
 *
 * @author SevenLeave
 * @date 2018-09-16
 */
public class ServiceMessage implements Serializable {
    private static final long serialVersionUID = 22699773302961299L;

    private boolean success = false;
    private String message = "";

    public ServiceMessage() {
    }

    public static ServiceMessage success(String message) {
        ServiceMessage serviceMessage = new ServiceMessage();
        serviceMessage.setSuccess(true);
        serviceMessage.setMessage(message);
        return serviceMessage;
    }

    public static ServiceMessage fail(String message) {
        ServiceMessage serviceMessage = new ServiceMessage();
        serviceMessage.setSuccess(false);
        serviceMessage.setMessage(message);
        return serviceMessage;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
