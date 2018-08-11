package cn.sevenleave.xmlconfig.utils.model;

import com.github.pagehelper.Page;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author SevenLeave
 * @date 2018-07-31 15:50
 */
public class PageResponse<T> implements Serializable {

    private static final long serialVersionUID = -1064416242697314496L;
    private long recordsTotal;
    private List<T> data = new ArrayList<>();
    private String error;

    public PageResponse(List<T> data) {
        if (data instanceof Page) {
            Page<T> page = (Page) data;
            this.recordsTotal = page.getTotal();
            this.data = data;
        }
    }

    public long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
