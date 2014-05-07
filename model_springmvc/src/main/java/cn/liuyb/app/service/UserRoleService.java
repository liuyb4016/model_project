package cn.liuyb.app.service;

import java.util.List;
import java.util.Map;

import cn.liuyb.app.domain.UserRole;

public interface UserRoleService {

	public void addUserRole(UserRole userRole);
	public List<UserRole> findUserRoles(int start, int max);
	public int countUserRoles();
	
	public UserRole findUserRole(long id);
	public void updateUserRole(UserRole userRole);
	public void deleteUserRole(UserRole userRole);
	public List<UserRole> findUserRoles();
	public Map<Object, String> mapUserRole();
}
