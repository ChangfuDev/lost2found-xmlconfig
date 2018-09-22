package cn.sevenleave.xmlconfig.business.owner.model;

import javax.persistence.*;

@Table(name = "sys_owner")
public class SysOwner {
    /**
     * 失物记录的uuid
     */
    @Id
    @Column(name = "release_record_uuid")
    private String releaseRecordUuid;

    /**
     * 物主姓名
     */
    @Column(name = "owner_name")
    private String ownerName;

    /**
     * 物主电话号码
     */
    @Column(name = "phone")
    private String phone;

    /**
     * 物主身份（如学生班级，校外人员）
     */
    @Column(name = "identity")
    private String identity;

    /**
     * 获取失物记录的uuid
     *
     * @return release_record_uuid - 失物记录的uuid
     */
    public String getReleaseRecordUuid() {
        return releaseRecordUuid;
    }

    /**
     * 设置失物记录的uuid
     *
     * @param releaseRecordUuid 失物记录的uuid
     */
    public void setReleaseRecordUuid(String releaseRecordUuid) {
        this.releaseRecordUuid = releaseRecordUuid == null ? null : releaseRecordUuid.trim();
    }

    /**
     * 获取物主姓名
     *
     * @return owner_name - 物主姓名
     */
    public String getOwnerName() {
        return ownerName;
    }

    /**
     * 设置物主姓名
     *
     * @param ownerName 物主姓名
     */
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName == null ? null : ownerName.trim();
    }

    /**
     * 获取物主电话号码
     *
     * @return phone - 物主电话号码
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置物主电话号码
     *
     * @param phone 物主电话号码
     */
    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    /**
     * 获取物主身份（如学生班级，校外人员）
     *
     * @return identity - 物主身份（如学生班级，校外人员）
     */
    public String getIdentity() {
        return identity;
    }

    /**
     * 设置物主身份（如学生班级，校外人员）
     *
     * @param identity 物主身份（如学生班级，校外人员）
     */
    public void setIdentity(String identity) {
        this.identity = identity == null ? null : identity.trim();
    }
}