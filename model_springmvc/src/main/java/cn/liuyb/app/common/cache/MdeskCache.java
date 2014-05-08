package cn.liuyb.app.common.cache;

import java.util.HashMap;
import java.util.Map;

public enum MdeskCache {
	
	//注意：enum的定义名和构造参数字符串必须满足以下关系  定义名  = 参数.toUpperCase(), 不能有空格
	UPDATE_CONFIG("update_config"), //升级配置
	TAIL("TAIL"),
	BOARD_KEY_MATCH("board_key_match");
	
	private String key;
	private final static String MAPSTART = "__MAP__";
	private final static String LINE = "_";
	private MdeskCache(String key){
		this.key = key;
	}
	
	@Override
	public String toString(){
		return this.key;
	}
	
	/**
	 * 刷新全部缓存
	 */
	public static void flushAll(){
		for(MdeskCache mc : MdeskCache.values()){
			MemCached.INSTANCE.delete(MAPSTART+mc.key);
			MemCached.INSTANCE.delete(mc.key);
		}
	}
	
	/**
	 * 从缓存取对象
	 * @param params 可以不传，存在参数时使用，如翻页的页码等。
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Object get(Object... params){
		if(params.length>0){
			Map<String,Boolean> cacheflagMap = (Map<String,Boolean>)MemCached.INSTANCE.get(MAPSTART+this.key);
			if(cacheflagMap==null){
				return null;
			}
			String requestKey = this.key;
			for(Object o : params){
				requestKey += LINE;
				requestKey += o;
			}
			if(cacheflagMap!=null && cacheflagMap.get(requestKey)!=null && true==(Boolean)cacheflagMap.get(requestKey)){
				return MemCached.INSTANCE.get(requestKey);
			}else {
				return null;
			}
		}else {
			return MemCached.INSTANCE.get(this.key);
		}
	}
	
	/**
	 * 将对象放进缓存（有参数时，也可全部刷新清除）
	 * @param value 放进去的值
	 * @param params 可以不传，存在参数时使用，如翻页的页码等。
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public void set(Object value, Object... params) {
		if(value==null){
			return ;
		}
		if(params.length>0){
			Map<String,Boolean> cacheflagMap = (Map<String,Boolean>)MemCached.INSTANCE.get(MAPSTART+this.key);
			if(cacheflagMap==null){
				cacheflagMap = new HashMap<String, Boolean>();
			}
			String requestKey = this.key;
			for(Object o : params){
				requestKey += LINE;
				requestKey += o;
			}
			MemCached.INSTANCE.set(requestKey,value);
			cacheflagMap.put(requestKey,true);
			MemCached.INSTANCE.set(MAPSTART+this.key,cacheflagMap);
		}else {
			MemCached.INSTANCE.set(this.key, value);
		}
	}

	/**
	 * 将对象放进缓存（无法后台刷新清除,较大量数据使用）
	 * @param value 放进去的值
	 * @param params 可以不传，存在参数时使用，如翻页的页码等。
	 * @return
	 */
	public void setOnly(Object value, Object... params) {
		String requestKey = this.key;
		for(Object o : params){
			requestKey += LINE;
			requestKey += o;
		}
		MemCached.INSTANCE.set(requestKey,value);
	}
	/**
	 * 将对象放进缓存（无法后台刷新清除,较大量数据使用）
	 * @param value 放进去的值
	 * @param expiry 超时时间，秒
	 * @param params 可以不传，存在参数时使用，如翻页的页码等。
	 * @return
	 */
	public void setOnly(Object value, Long expiry, Object... params) {
		String requestKey = this.key;
		for(Object o : params){
			requestKey += LINE;
			requestKey += o;
		}
		MemCached.INSTANCE.set(requestKey,value,expiry);
	}
	
	/**
	 * 从缓存取对象（无法后台刷新清除,较大量数据使用）
	 * @param params 可以不传，存在参数时使用，如翻页的页码等。
	 * @return
	 */
	public Object getOnly(Object... params){
		String requestKey = this.key;
		for(Object o : params){
			requestKey += LINE;
			requestKey += o;
		}
		return MemCached.INSTANCE.get(requestKey);
	}
	
}
