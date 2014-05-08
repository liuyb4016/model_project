package cn.liuyb.app.portal.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.liuyb.app.portal.dao.UserRoleDao;
import cn.liuyb.app.portal.domain.UserRole;
import cn.liuyb.app.portal.service.UserRoleService;

@Service
public class UserRoleServiceImpl implements UserRoleService {

	@Autowired
	private UserRoleDao userRoleDao;

	@Override
	@Transactional
	public void addUserRole(UserRole userRole) {
		userRoleDao.create(userRole);
	}

	@Transactional(readOnly = true)
	@Override
	public List<UserRole> findUserRoles(int start, int max) {
		return userRoleDao.findUserRoles(start, max);
	}

	@Transactional(readOnly = true)
	@Override
	public int countUserRoles() {
		return userRoleDao.countUserRoles();
	}

	@Transactional(readOnly = true)
	@Override
	public UserRole findUserRole(long id) {
		return userRoleDao.find(id);
	}

	@Override
	@Transactional
	public void updateUserRole(UserRole userRole) {
		userRoleDao.update(userRole);
	}

	@Override
	@Transactional
	public void deleteUserRole(UserRole userRole) {
		userRoleDao.delete(userRole);
	}

	@Transactional(readOnly = true)
	@Override
	public List<UserRole> findUserRoles() {
		return userRoleDao.findUserRoles();
	}

	@Override
	public Map<Object, String> mapUserRole() {
		Map<Object, String> map = new HashMap<Object, String>();
		List<UserRole> list = this.findUserRoles();
		for (UserRole userRole : list) {
			map.put(userRole.getId(), userRole.getRoleName());
		}

		return map;
	}

}
