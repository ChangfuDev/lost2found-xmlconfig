package cn.sevenleave.xmlconfig.admin.model;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "admin_info")
public class AdminInfo implements Serializable {

    private static final long serialVersionUID = 1087520814784441190L;

    /**
     * uuid
     */
    @Id
    @Column(name = "uuid")
    private String uuid;

    /**
     * 管理员的名称
     */
    @Column(name = "admin_name")
    private String adminName;

    /**
     * 管理员的密码
     */
    @Column(name = "admin_psd")
    private String adminPsd;

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
     * @return admin_name - 管理员的名称
     */
    public String getAdminName() {
        return adminName;
    }

    /**
     * 设置管理员的名称
     *
     * @param adminName 管理员的名称
     */
    public void setAdminName(String adminName) {
        this.adminName = adminName == null ? null : adminName.trim();
    }

    /**
     * 获取管理员的密码
     *
     * @return admin_psd - 管理员的密码
     */
    public String getAdminPsd() {
        return adminPsd;
    }

    /**
     * 设置管理员的密码
     *
     * @param adminPsd 管理员的密码
     */
    public void setAdminPsd(String adminPsd) {
        this.adminPsd = adminPsd == null ? null : adminPsd.trim();
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