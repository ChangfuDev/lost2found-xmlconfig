package cn.sevenleave.xmlconfig.waiter.model;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "waiter_info")
public class WaiterInfo implements Serializable {

    private static final long serialVersionUID = -687302496591873861L;

    /**
     * uuid
     */
    @Id
    @Column(name = "uuid")
    private String uuid;

    /**
     * 服务员名
     */
    @Column(name = "waiter_name")
    private String waiterName;

    /**
     * 服务员密码
     */
    @Column(name = "waiter_psd")
    private String waiterPsd;

    /**
     * 手机号
     */
    @Column(name = "phone")
    private String phone;

    /**
     * 获取uuid
     *
     * @return uuid - uuid
     */
    public String getUuid() {
        return uuid;
    }

    /**
     * 设置uuid
     *
     * @param uuid uuid
     */
    public void setUuid(String uuid) {
        this.uuid = uuid == null ? null : uuid.trim();
    }

    /**
     * 获取服务员名
     *
     * @return waiter_name - 服务员名
     */
    public String getWaiterName() {
        return waiterName;
    }

    /**
     * 设置服务员名
     *
     * @param waiterName 服务员名
     */
    public void setWaiterName(String waiterName) {
        this.waiterName = waiterName == null ? null : waiterName.trim();
    }

    /**
     * 获取服务员密码
     *
     * @return waiter_psd - 服务员密码
     */
    public String getWaiterPsd() {
        return waiterPsd;
    }

    /**
     * 设置服务员密码
     *
     * @param waiterPsd 服务员密码
     */
    public void setWaiterPsd(String waiterPsd) {
        this.waiterPsd = waiterPsd == null ? null : waiterPsd.trim();
    }

    /**
     * 获取手机号
     *
     * @return phone - 手机号
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置手机号
     *
     * @param phone 手机号
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }
}