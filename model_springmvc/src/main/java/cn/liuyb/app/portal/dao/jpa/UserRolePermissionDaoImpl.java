package cn.liuyb.app.portal.dao.jpa;


import java.util.List;

import org.springframework.stereotype.Repository;

import cn.liuyb.app.common.dao.AbstractFlushModeCommitDao;
import cn.liuyb.app.portal.dao.UserRolePermissionDao;
import cn.liuyb.app.portal.domain.UserRolePermission;

@Repository
public class UserRolePermissionDaoImpl extends AbstractFlushModeCommitDao<UserRolePermission> implements
UserRolePermissionDao {

	public UserRolePermissionDaoImpl() {
		super(UserRolePermission.class);
	}

	@Override
	public List<UserRolePermission> listAll() {
		return this.findByProperties("UserRolePermission.findUserRolePermission",new Object[]{});
	}

	@Override
	public List<UserRolePermission> findUserRolePermissions(int start, int max) {
		return this.findByProperties("UserRolePermission.findUserRolePermission", start, max,new Object[]{});
	}

	@Override
	public int countAll() {
		return this.countByProperties("UserRolePermission.countUserRolePermission",new Object[]{});
	}
	
	@Override
	public List<UserRolePermission> findUserRolePermissionsByRoleId(long roleId) {
		return this.findByProperties("UserRolePermission.findUserRolePermissionByRoleId",new Object[]{p("q",roleId)});
	}
}
