package cn.sevenleave.xmlconfig.user.model;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "user_info")
public class UserInfo implements Serializable {

    private static final long serialVersionUID = -1572027964226773029L;

    /**
     * uuid
     */
    @Id
    @Column(name = "uuid")
    private String uuid;

    /**
     * 用户名
     */
    @Column(name = "user_name")
    private String userName;

    /**
     * 密码
     */
    @Column(name = "user_psd")
    private String userPsd;

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
     * @return user_name - 用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名
     *
     * @param userName 用户名
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 获取密码
     *
     * @return user_psd - 密码
     */
    public String getUserPsd() {
        return userPsd;
    }

    /**
     * 设置密码
     *
     * @param userPsd 密码
     */
    public void setUserPsd(String userPsd) {
        this.userPsd = userPsd == null ? null : userPsd.trim();
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