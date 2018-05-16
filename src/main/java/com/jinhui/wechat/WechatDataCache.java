package com.jinhui.wechat;

import com.jinhui.util.RedisUtils;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by jinhui on 2017/8/25.
 * // FIXME: 2017/9/26 修改redis缓存方式
 */
public class WechatDataCache {

    private final ReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock readLock = lock.readLock();
    private final Lock writeLock = lock.writeLock();
    private static Map<String, CacheObject> CacheMap = new HashMap<>();

    private final long expiredTime;
    private final boolean enabledRedisCache;
    private final ScheduledExecutorService validationService;

    private static class SingletoInstance {
        static WechatDataCache instance = new WechatDataCache();
    }

    public static WechatDataCache instance(){
        return SingletoInstance.instance;
    }

    public WechatDataCache() {
        this(false);
    }

    public WechatDataCache(boolean enabledRedisCache) {
        this(7000 * 1000, enabledRedisCache);
    }

    public WechatDataCache(long expiredTime, boolean enabledRedisCache) {
        this.enabledRedisCache = enabledRedisCache;
        this.expiredTime = expiredTime;
        if(!enabledRedisCache) {
            /**
             * 在本地完成expired清理动作
             */
            this.validationService = Executors.newSingleThreadScheduledExecutor();
            this.validationService.scheduleAtFixedRate(cleanJob, this.expiredTime,
                    this.expiredTime, TimeUnit.MILLISECONDS);
        } else
            this.validationService = null;
    }

    private Runnable cleanJob = new Runnable() {
        @Override
        public void run() {
            /**
             * 清理过期验证码
             */
            Iterator<String> iter = CacheMap.keySet().iterator();
            while (iter.hasNext()){
                String key = iter.next();
                // 由于并发缘故，{@link WechatDataCache#getCode(String)}
                // 可能已经把该{@param key}对应的Code, 清理掉了
                CacheObject code = CacheMap.get(key);
                if(code != null && code.isValidation(expiredTime)){
                    iter.remove();
                }
            }
        }
    };

    public String get(String key) {
        readLock.lock();
        try {
            if (enabledRedisCache) {
                return (String) RedisUtils.getValue(key);
            }
            CacheObject c = CacheMap.get(key);
            if (c == null)
                return null;
            if (!c.isValidation(expiredTime)) {
                CacheMap.remove(key);
                return null;
            }
            return c.value;
        } finally {
            readLock.unlock();
        }
    }

    public boolean exists(String key, String value) {
        readLock.lock();
        try {
            if (enabledRedisCache) {
                return RedisUtils.getValue(key) != null;
            }
            CacheObject c = CacheMap.get(key);
            if (c == null)
                return false;
            if (!c.isValidation(expiredTime)) {
                CacheMap.remove(key);
                return false;
            }
            return c.has(value);
        } finally {
            readLock.unlock();
        }
    }

    public void put(String key, String value) {
        writeLock.lock();
        try {
            if (enabledRedisCache) {
                RedisUtils.setValue(key, value, expiredTime, TimeUnit.MILLISECONDS);
                return;
            }
            CacheMap.put(key, new CacheObject(new Date(), value));
        } finally {
            writeLock.unlock();
        }
    }

    /**
     * 使用key-value形式缓存具体数据对象的索引。
     * 由于前后端完全分离，signature需要通过后台请求微信服务器生成，
     * 前台请求得到的signature无法在ajax结果中配置(也即是signature应当在浏览器解释js时配置好)，
     * 并且每一个页面都需要一份signature，而所有的signature又共享同一个access_token和jsapi_ticket;
     * 因此， key对应的value关联所有signature相关。
     * @param key key:js文件目录
     * @param valueAction value：所有的js文件名
     */
    public void put(String key, FileValuesAction valueAction) {
        writeLock.lock();
        try {
            if (enabledRedisCache) {
                RedisUtils.setValue(key, valueAction.doAction(), expiredTime, TimeUnit.MILLISECONDS);
                return;
            }
            CacheObject co = valueAction.addAction(key, CacheMap);
            //如果是重复的值，则为null
            if(co != null) {
                CacheMap.put(key, co);
            }
        } finally {
            writeLock.unlock();
        }
    }

    public static abstract class FileValuesAction {
        private final String value;
        public FileValuesAction(String value) {
            this.value = value;
        }
        public String value() {
            return this.value;
        }

        /**
         * 对已加入的{@param value}返回null，并不做任何处理
         * @param key
         * @param cacheMap
         * @return
         */
        protected CacheObject addAction(String key, Map<String, CacheObject> cacheMap) {
            CacheObject co = cacheMap.get(key);
            if(co != null){
                //已经加入的值，不需要再添加
                if (co.has(value))
                    return null;
            }
            //执行文件更新操作
            String filename = doAction();
            return co == null ? new CacheObject(new Date(), filename, true)
                    : co.addValue(filename);
        }
        /**
         * 在返回文件名之前，允许执行相关的文件操作
         * @return 文件名
         */
        abstract String doAction();
    }

    private static class CacheObject {
        final Date createdTime;
        String value;
        final boolean multiValue;
        final static String SPLIT = ",";

        public CacheObject(Date createdTime, String value) {
            this(createdTime, value, false);
        }

        public CacheObject(Date createdTime, String value, boolean multiValue) {
            this.createdTime = createdTime;
            this.value = value;
            this.multiValue = multiValue;
        }

        public boolean isMultiValue() {
            return multiValue;
        }

        public boolean has(String v){
            String[] values = value.split(SPLIT);
            for(String value:values){
                if(v.equals(value))
                    return true;
            }
            return false;
        }

        public CacheObject addValue(String value){
            Assert.isTrue(this.multiValue, "不允许添加多个值");
            this.value = this.value + SPLIT + value;
            return this;
        }

        public boolean isValidation(long expiredTime) {
            long duration = System.currentTimeMillis() - createdTime.getTime();
            return duration < expiredTime;
        }
    }

    public static void main(String[] strs) {
        CacheObject co = new CacheObject(new Date(), "_v_alue");
        co.addValue("_v");

        System.out.println(co.has("_v"));
    }
}
