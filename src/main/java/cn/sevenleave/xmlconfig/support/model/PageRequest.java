package cn.sevenleave.xmlconfig.support.model;

import java.io.Serializable;
import java.util.Map;

/**
 * @author SevenLeave
 * @date 2018-07-31 14:52
 */
public class PageRequest implements Serializable {

    private static final long serialVersionUID = 949438445686741902L;
    private int pageNum;
    private int pageSize;
    private Map<String, Object> paramMap;

    public PageRequest() {
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }
}
