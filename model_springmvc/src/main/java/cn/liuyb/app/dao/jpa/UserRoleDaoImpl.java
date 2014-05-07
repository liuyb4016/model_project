package cn.liuyb.app.dao.jpa;


import java.util.List;

import org.springframework.stereotype.Repository;

import cn.liuyb.app.common.dao.AbstractFlushModeCommitDao;
import cn.liuyb.app.dao.UserRoleDao;
import cn.liuyb.app.domain.UserRole;

@Repository
public class UserRoleDaoImpl extends AbstractFlushModeCommitDao<UserRole>
		implements UserRoleDao {

	public UserRoleDaoImpl() {
		super(UserRole.class);
	}

	@Override
	public List<UserRole> findUserRoles(int start, int max) {
		return this.findByProperties("UserRole.findUserRole", start, max,new Object[]{});
	}

	@Override
	public int countUserRoles() {
		return this.countByProperties("UserRole.countUserRole",new Object[]{});
	}

	@Override
	public List<UserRole> findUserRoles() {
		return this.findByProperties("UserRole.findUserRole",new Object[]{});
	}
}
