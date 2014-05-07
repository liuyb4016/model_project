package cn.liuyb.app.dao;


import java.util.List;

import cn.liuyb.app.common.dao.EntityDao;
import cn.liuyb.app.domain.UserRolePermission;

public interface UserRolePermissionDao extends EntityDao<UserRolePermission> {
	
	public List<UserRolePermission> listAll();
	
	public List<UserRolePermission> findUserRolePermissions(int start,int max);
	
	public int countAll();
	
	public List<UserRolePermission> findUserRolePermissionsByRoleId(long roleId);
}
