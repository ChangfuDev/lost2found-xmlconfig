package cn.sevenleave.xmlconfig.utils.model;

import java.io.Serializable;

/**
 * @author SevenLeave
 * @date 2018-07-30 14:48
 */
public class ErrorInfo implements Serializable {

    private static final long serialVersionUID = 6324652372150078026L;

    private String field;
    private String info;

    public ErrorInfo() {
    }


    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
