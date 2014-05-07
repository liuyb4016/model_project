package cn.liuyb.app.service;

import java.util.List;

import cn.liuyb.app.domain.UserRolePermission;

public interface UserRolePermissionService {

	public void addUserRolePermission(UserRolePermission userRolePermission);
	public List<UserRolePermission> listUserRolePermission();
	public List<UserRolePermission> findUserRolePermissions(int start,int max);
	public int countAll();
	public void transAddRolePermission(List<UserRolePermission> list);
	public List<UserRolePermission> findUserRolePermissionsByRoleId(long roleId);
	public void deleteAndInsertPermission(long roleId,String[] ids);
	public void setMemCachedInsertPermission(long roleId);

}
