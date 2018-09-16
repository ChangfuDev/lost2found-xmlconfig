package cn.sevenleave.xmlconfig.business.lost.release.record.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "lost_release_record")
public class LostReleaseRecord implements Serializable {
    private static final long serialVersionUID = 7429118714468285467L;

    /**
     * uuid
     */
    @Id
    @Column(name = "uuid")
    private String uuid;

    /**
     * 作为失物的uuid
     */
    @Column(name = "cache_record_uuid")
    private String cacheRecordUuid;

    /**
     * 失物上架时间
     */
    @Column(name = "release_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date releaseTime;

    /**
     * 状态：2-主动下架，3-找到失主
     */
    @Column(name = "status")
    private String status;

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
     * 获取作为失物的uuid
     *
     * @return cache_record_uuid - 作为失物的uuid
     */
    public String getCacheRecordUuid() {
        return cacheRecordUuid;
    }

    /**
     * 设置作为失物的uuid
     *
     * @param cacheRecordUuid 作为失物的uuid
     */
    public void setCacheRecordUuid(String cacheRecordUuid) {
        this.cacheRecordUuid = cacheRecordUuid == null ? null : cacheRecordUuid.trim();
    }

    /**
     * 获取失物上架时间
     *
     * @return release_time - 失物上架时间
     */
    public Date getReleaseTime() {
        return releaseTime;
    }

    /**
     * 设置失物上架时间
     *
     * @param releaseTime 失物上架时间
     */
    public void setReleaseTime(Date releaseTime) {
        this.releaseTime = releaseTime;
    }

    /**
     * 获取状态：2-主动下架，3-找到失主
     *
     * @return status - 状态：2-主动下架，3-找到失主
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态：2-主动下架，3-找到失主
     *
     * @param status 状态：2-主动下架，3-找到失主
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}