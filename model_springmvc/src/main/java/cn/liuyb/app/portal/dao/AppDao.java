package cn.liuyb.app.portal.dao;

import java.util.List;

import cn.liuyb.app.common.dao.EntityDao;
import cn.liuyb.app.portal.domain.App;

public interface AppDao extends EntityDao<App> {
	
	public static final String APP = "MDESK_APP_M:";
	public List<App> findAll();
	public int countAll();
	public App findByPackageName(String packageName);
		
}
