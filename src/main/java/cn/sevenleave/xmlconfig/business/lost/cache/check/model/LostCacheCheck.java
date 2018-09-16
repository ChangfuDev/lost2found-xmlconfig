package cn.sevenleave.xmlconfig.business.lost.cache.check.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "lost_cache_check")
public class LostCacheCheck implements Serializable {
    private static final long serialVersionUID = -1820535388754379283L;

    /**
     * uuid
     */
    @Id
    @Column(name = "uuid")
    private String uuid;

    /**
     * 服务员uuid
     */
    @Column(name = "waiter_uuid")
    private String waiterUuid;

    /**
     * 作为失物的uuid
     */
    @Column(name = "cache_record_uuid")
    private String cacheRecordUuid;

    /**
     * 操作：1-通过，-1-不合规，
     */
    @Column(name = "handle")
    private String handle;

    /**
     * 操作的时间戳
     */
    @Column(name = "handle_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date handleTime;

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
     * 获取服务员uuid
     *
     * @return waiter_uuid - 服务员uuid
     */
    public String getWaiterUuid() {
        return waiterUuid;
    }

    /**
     * 设置服务员uuid
     *
     * @param waiterUuid 服务员uuid
     */
    public void setWaiterUuid(String waiterUuid) {
        this.waiterUuid = waiterUuid == null ? null : waiterUuid.trim();
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
     * 获取操作：1-通过，-1-不合规，
     *
     * @return handle - 操作：1-通过，-1-不合规，
     */
    public String getHandle() {
        return handle;
    }

    /**
     * 设置操作：1-通过，-1-不合规，
     *
     * @param handle 操作：1-通过，-1-不合规，
     */
    public void setHandle(String handle) {
        this.handle = handle == null ? null : handle.trim();
    }

    /**
     * 获取操作的时间戳
     *
     * @return handle_time - 操作的时间戳
     */
    public Date getHandleTime() {
        return handleTime;
    }

    /**
     * 设置操作的时间戳
     *
     * @param handleTime 操作的时间戳
     */
    public void setHandleTime(Date handleTime) {
        this.handleTime = handleTime;
    }
}