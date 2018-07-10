package com.abs.springabscommon.bean.cache;

import java.io.Serializable;

/**
 * @author:XieZhengJun
 * @description:
 * @date: Created in 16:08 2018/7/5
 **/
public class CacheItemConfig implements Serializable {

    /**
     * 缓存容器名称
     */
    private String name;
    /**
     * 缓存失效时间
     */
    private long expiryTimeSecond;
    /**
     * 当缓存存活时间达到此值时，主动刷新缓存
     */
    private long preLoadTimeSecond;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getExpiryTimeSecond() {
        return expiryTimeSecond;
    }

    public void setExpiryTimeSecond(long expiryTimeSecond) {
        this.expiryTimeSecond = expiryTimeSecond;
    }

    public long getPreLoadTimeSecond() {
        return preLoadTimeSecond;
    }

    public void setPreLoadTimeSecond(long preLoadTimeSecond) {
        this.preLoadTimeSecond = preLoadTimeSecond;
    }
}
