package cn.liuyb.app.service;

import java.util.List;

import cn.liuyb.app.domain.RoleUrl;

public interface RoleUrlService {

	public List<RoleUrl> findRoleUrlByClassName(String className);
	
	public List<Object> findRoleUrlGroupByClassName();

	public boolean isBeingRoleUrlByUrlAndType(String url);

	public void add(RoleUrl roleUrl);
	public void delete(RoleUrl roleUrl);
	public void update(RoleUrl roleUrl);
	
	public int countRoleUrlByClassName(String className);
	
	public List<RoleUrl> findRoleUrlByClassName(int start,int max,String className);
	
	public int countRoleUrlGroupByClassName();
	
	public List<Object> findRoleUrlGroupByClassName(int start,int max);
	
	public RoleUrl getRoleUrlById(Long id);
	
	public List<RoleUrl> findRoleUrlByUrlAndTypeReadOnly(String url,Integer readOnly);

	public boolean isBeingRoleUrlByUrlAndTypeReadOnly(String url,Integer readOnly);
	
	public List<RoleUrl> findRoleUrlByUserFunctionModel(String userFunctionModel);

}
