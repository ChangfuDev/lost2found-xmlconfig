package cn.sevenleave.xmlconfig.test.update.model;

import java.util.Date;
import javax.persistence.*;

@Table(name = "lost_release_update")
public class LostReleaseUpdate {
    /**
     * uuid
     */
    @Id
    @Column(name = "uuid")
    private String uuid;

    /**
     * waiter的uuid
     */
    @Column(name = "waiter_uuid")
    private String waiterUuid;

    /**
     * 作为失物的uuid
     */
    @Column(name = "cache_record_uuid")
    private String cacheRecordUuid;

    /**
     * 操作：2-主动下架，3-已找到失主
     */
    @Column(name = "handle")
    private String handle;

    /**
     * 操作的时间戳
     */
    @Column(name = "handle_time")
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
     * 获取waiter的uuid
     *
     * @return waiter_uuid - waiter的uuid
     */
    public String getWaiterUuid() {
        return waiterUuid;
    }

    /**
     * 设置waiter的uuid
     *
     * @param waiterUuid waiter的uuid
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
     * 获取操作：2-主动下架，3-已找到失主
     *
     * @return handle - 操作：2-主动下架，3-已找到失主
     */
    public String getHandle() {
        return handle;
    }

    /**
     * 设置操作：2-主动下架，3-已找到失主
     *
     * @param handle 操作：2-主动下架，3-已找到失主
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