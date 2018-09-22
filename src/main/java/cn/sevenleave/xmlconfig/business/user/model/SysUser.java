package cn.sevenleave.xmlconfig.business.user.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 描述：表 sys_user 对应的实体类.
 */
@Table(name = "sys_user")
public class SysUser implements Serializable {

    private static final long serialVersionUID = -9186692185989739306L;

    /**
     * uuid
     */
    @Id
    @Column(name = "uuid")
    private String uuid;

    /**
     * 用户名
     */
    @Column(name = "username")
    private String username;

    /**
     * 密码加盐hash值
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
     * 获取用户名
     *
     * @return username - 用户名
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置用户名
     *
     * @param username 用户名
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * 获取密码加盐hash值
     *
     * @return password - 密码加盐hash值
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码加盐hash值
     *
     * @param password 密码加盐hash值
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