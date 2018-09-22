package cn.sevenleave.xmlconfig.business.waiter.model;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "sys_waiter")
public class SysWaiter implements Serializable {
    private static final long serialVersionUID = 1622233620332622442L;

    /**
     * uuid
     */
    @Id
    @Column(name = "uuid")
    private String uuid;

    /**
     * 服务员名
     */
    @Column(name = "username")
    private String username;

    /**
     * 服务员密码
     */
    @Column(name = "password")
    private String password;

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
     * @return username - 服务员名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置服务员名
     *
     * @param username 服务员名
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * 获取服务员密码
     *
     * @return password - 服务员密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置服务员密码
     *
     * @param password 服务员密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
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