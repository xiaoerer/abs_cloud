package com.abs.springabscommon.bean.cache;

/**
 * @author:XieZhengJun
 * @description:
 * @date: Created in 16:28 2018/7/5
 * 主动刷新缓存接口
 **/
public interface CacheSupport {

    /**
     * 刷新容器中所有值
     * @param cachename
     */
    void refreshCache(String cachename);

    /**
     * 按容器以及指定键更新缓存
     * @param cacheName
     * @param cacheKey
     */
    void refreshCacheByKey(String cacheName,String cacheKey);

}
