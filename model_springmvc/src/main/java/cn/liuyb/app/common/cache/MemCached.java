package cn.liuyb.app.common.cache;

import java.util.concurrent.TimeoutException;

import javax.annotation.Resource;

import net.rubyeye.xmemcached.XMemcachedClient;
import net.rubyeye.xmemcached.exception.MemcachedException;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Value;

import cn.liuyb.app.common.utils.Slf4jLogUtils;

public class MemCached {
    private static Logger logger = Slf4jLogUtils.getLogger(MemCached.class);
  
    public static MemCached INSTANCE = new MemCached();

    @Resource(name="memcachedClient")
    private XMemcachedClient memcachedClient;
    
    @Value("${xmemcached.expiry}")
    private Integer expiry=0;
    @Value("${xmemcached.servers}")
    public String servers;
    
    private MemCached(){}
    
    public Object get(String key) {
    	
    	if(StringUtils.isNotBlank(key)){
    		key=key.replace(" ", "_");
    	}
        logger.debug("get('{}')", key);
        try {
			return memcachedClient.get(key);
		} catch (TimeoutException e) {
			logger.error("TimeoutException get('{}')", key);
		} catch (InterruptedException e) {
			logger.error("InterruptedException get('{}')", key);
		} catch (MemcachedException e) {
			logger.error("MemcachedException get('{}')", key);
		} catch (Exception e){
			logger.error("Exception key={}, e={}", key, e);
		}
        return null;
    }

    public void set(String key, Object value) {

    	if(StringUtils.isNotBlank(key)){
    		key=key.replace(" ", "_");
    	}
        logger.debug("set('{}', '{}')", key, value);
        try {
        	memcachedClient.set(key, this.expiry, value);
		} catch (TimeoutException e) {
			logger.error("TimeoutException set('{}', '{}')", key, value+", "+this.expiry);
		} catch (InterruptedException e) {
			logger.error("InterruptedException set('{}, '{}')", key, value+", "+this.expiry);
		} catch (MemcachedException e) {
			logger.error("MemcachedException set('{}, '{}')", key, value+", "+this.expiry);
		} catch (Exception e){
			logger.error("Exception key={}, e={}", key, e);
		}
    }

    public void set(String key, Object value,Long expiry) {
    	if(StringUtils.isNotBlank(key)){
    		key=key.replace(" ", "_");
    	}
        logger.debug("set('{}', '{}')", key, value+", "+expiry);
        try {
        	memcachedClient.set(key, expiry.intValue(), value);
		} catch (TimeoutException e) {
			logger.error("TimeoutException set('{}', '{}')", key, value+", "+expiry.intValue());
		} catch (InterruptedException e) {
			logger.error("InterruptedException set('{}, '{}')", key, value+", "+expiry.intValue());
		} catch (MemcachedException e) {
			logger.error("MemcachedException set('{}, '{}')", key, value+", "+expiry.intValue());
		} catch (Exception e){
			logger.error("Exception key={}, e={}", key, e);
		}
    }


    public void delete(String key) {
    	if(StringUtils.isNotBlank(key)){
    		key=key.replace(" ", "_");
    	}
        logger.debug("delete('{}')", key);
        try {
        	memcachedClient.delete(key);
		} catch (TimeoutException e) {
			logger.error("TimeoutException delete('{}')", key);
		} catch (InterruptedException e) {
			logger.error("InterruptedException delete('{}')", key);
		} catch (MemcachedException e) {
			logger.error("MemcachedException delete('{}')", key);
		} catch (Exception e){
			logger.error("Exception key={}, e={}", key, e);
		}
    }
    
    public boolean add(String key,int expiry,Object value) {
    	boolean isAdd = false;
    	if(StringUtils.isNotBlank(key)){
    		key=key.replace(" ", "_");
    	}
        logger.debug("set('{}', '{}')", key, value+", "+expiry);
        try {
        	isAdd = memcachedClient.add(key, expiry, value);
		} catch (TimeoutException e) {
			logger.error("TimeoutException set('{}', '{}')", key, value+", "+expiry);
		} catch (InterruptedException e) {
			logger.error("InterruptedException set('{}, '{}')", key, value+", "+expiry);
		} catch (MemcachedException e) {
			logger.error("MemcachedException set('{}, '{}')", key, value+", "+expiry);
		} catch (Exception e){
			logger.error("Exception key={}, e={}", key, e);
		}
        return isAdd;
    }
}
