package cn.sevenleave.xmlconfig.cache.handle.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Table(name = "cache_handle")
public class CacheHandle implements Serializable {

    private static final long serialVersionUID = 4938192949624421099L;

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
     * 失物的uuid
     */
    @Column(name = "cache_uuid")
    private String cacheUuid;

    /**
     * 操作：0-驳回，1-通过
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
     * 获取失物的uuid
     *
     * @return cache_uuid - 失物的uuid
     */
    public String getCacheUuid() {
        return cacheUuid;
    }

    /**
     * 设置失物的uuid
     *
     * @param cacheUuid 失物的uuid
     */
    public void setCacheUuid(String cacheUuid) {
        this.cacheUuid = cacheUuid == null ? null : cacheUuid.trim();
    }

    /**
     * 获取操作：0-驳回，1-通过
     *
     * @return handle - 操作：0-驳回，1-通过
     */
    public String getHandle() {
        return handle;
    }

    /**
     * 设置操作：0-驳回，1-通过
     *
     * @param handle 操作：0-驳回，1-通过
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