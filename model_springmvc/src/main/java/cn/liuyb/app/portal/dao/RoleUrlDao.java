package cn.liuyb.app.portal.dao;

import java.util.List;

import cn.liuyb.app.common.dao.EntityDao;
import cn.liuyb.app.portal.domain.RoleUrl;

public interface RoleUrlDao  extends EntityDao<RoleUrl>{

	public List<RoleUrl> findRoleUrlByClassName(String className);
	
	public int countRoleUrlByClassName(String className);
	
	public List<RoleUrl> findRoleUrlByClassName(int start,int max,String className);
	
	public List<Object> findRoleUrlGroupByClassName();
	
	public int countRoleUrlGroupByClassName();
	
	public List<Object> findRoleUrlGroupByClassName(int start,int max);
	
	public List<RoleUrl> findRoleUrlByUrlAndType(String url);

	public boolean isBeingRoleUrlByUrlAndType(String url);

	public List<RoleUrl> findRoleUrlByUrlAndTypeReadOnly(String url,Integer readOnly);

	public boolean isBeingRoleUrlByUrlAndTypeReadOnly(String url,Integer readOnly);

	public List<RoleUrl> findRoleUrlByUserFunctionModel(String userFunctionModel);

}
