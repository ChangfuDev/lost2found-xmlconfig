package cn.sevenleave.xmlconfig.business.lost.cache.record.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "lost_cache_record")
public class LostCacheRecord implements Serializable {

    private static final long serialVersionUID = -1051472351863414347L;

    /**
     * uuid
     */
    @Id
    @Column(name = "uuid")
    private String uuid;

    /**
     * 用户uuid
     */
    @Column(name = "user_uuid")
    private String userUuid;

    /**
     * 招领物品的关键词
     */
    @Column(name = "keyword")
    private String keyword;

    /**
     * 招领物品的描述
     */
    @Column(name = "description")
    private String description;

    /**
     * 捡到失物的时间
     */
    @Column(name = "pickup_time")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date pickupTime;

    /**
     * 发布招领的时间戳
     */
    @Column(name = "submit_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date submitTime;

    /**
     * 状态：0-未处理，-1-不合规，1-通过，2-主动下架
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
     * 获取用户uuid
     *
     * @return user_uuid - 用户uuid
     */
    public String getUserUuid() {
        return userUuid;
    }

    /**
     * 设置用户uuid
     *
     * @param userUuid 用户uuid
     */
    public void setUserUuid(String userUuid) {
        this.userUuid = userUuid == null ? null : userUuid.trim();
    }

    /**
     * 获取招领物品的关键词
     *
     * @return keyword - 招领物品的关键词
     */
    public String getKeyword() {
        return keyword;
    }

    /**
     * 设置招领物品的关键词
     *
     * @param keyword 招领物品的关键词
     */
    public void setKeyword(String keyword) {
        this.keyword = keyword == null ? null : keyword.trim();
    }

    /**
     * 获取招领物品的描述
     *
     * @return description - 招领物品的描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置招领物品的描述
     *
     * @param description 招领物品的描述
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 获取捡到失物的时间
     *
     * @return pickup_time - 捡到失物的时间
     */
    public Date getPickupTime() {
        return pickupTime;
    }

    /**
     * 设置捡到失物的时间
     *
     * @param pickupTime 捡到失物的时间
     */
    public void setPickupTime(Date pickupTime) {
        this.pickupTime = pickupTime;
    }

    /**
     * 获取发布招领的时间戳
     *
     * @return submit_time - 发布招领的时间戳
     */
    public Date getSubmitTime() {
        return submitTime;
    }

    /**
     * 设置发布招领的时间戳
     *
     * @param submitTime 发布招领的时间戳
     */
    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    /**
     * 获取状态：0-未处理，-1-不合规，1-通过，2-主动下架
     *
     * @return status - 状态：0-未处理，-1-不合规，1-通过，2-主动下架
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态：0-未处理，-1-不合规，1-通过，2-主动下架
     *
     * @param status 状态：0-未处理，-1-不合规，1-通过，2-主动下架
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}