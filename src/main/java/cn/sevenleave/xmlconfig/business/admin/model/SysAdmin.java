package cn.sevenleave.xmlconfig.business.admin.model;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 描述：表 sys_admin 对应的实体类.
 */
@Table(name = "sys_admin")
public class SysAdmin implements Serializable {

    private static final long serialVersionUID = 5177877682141854404L;

    /**
     * uuid
     */
    @Id
    @Column(name = "uuid")
    private String uuid;

    /**
     * 管理员的名称
     */
    @Column(name = "username")
    private String username;

    /**
     * 管理员的密码加盐hash值
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
     * 获取管理员的名称
     *
     * @return username - 管理员的名称
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置管理员的名称
     *
     * @param username 管理员的名称
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * 获取管理员的密码加盐hash值
     *
     * @return password - 管理员的密码加盐hash值
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置管理员的密码加盐hash值
     *
     * @param password 管理员的密码加盐hash值
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