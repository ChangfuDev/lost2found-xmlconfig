package cn.sevenleave.xmlconfig.found.record.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "found_record")
public class FoundRecord implements Serializable {

    private static final long serialVersionUID = 6668388954327315415L;

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
     * 信息发布日期
     */
    @Column(name = "pub_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date pubTime;

    /**
     * 状态：0-发布中，1-已找到，2-未知下架
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
     * 获取信息发布日期
     *
     * @return pub_time - 信息发布日期
     */
    public Date getPubTime() {
        return pubTime;
    }

    /**
     * 设置信息发布日期
     *
     * @param pubTime 信息发布日期
     */
    public void setPubTime(Date pubTime) {
        this.pubTime = pubTime;
    }

    /**
     * 获取状态：0-发布中，1-已找到，2-未知下架
     *
     * @return status - 状态：0-发布中，1-已找到，2-未知下架
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置状态：0-发布中，1-已找到，2-未知下架
     *
     * @param status 状态：0-发布中，1-已找到，2-未知下架
     */
    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }
}