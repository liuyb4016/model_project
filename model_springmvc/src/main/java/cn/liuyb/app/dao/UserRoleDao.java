package cn.liuyb.app.dao;


import java.util.List;

import cn.liuyb.app.common.dao.EntityDao;
import cn.liuyb.app.domain.UserRole;

public interface UserRoleDao extends EntityDao<UserRole> {
	
	public List<UserRole> findUserRoles(int start, int max);
	public List<UserRole> findUserRoles();
	public int countUserRoles();
}
