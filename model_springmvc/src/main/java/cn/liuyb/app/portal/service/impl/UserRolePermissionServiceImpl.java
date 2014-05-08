package cn.liuyb.app.portal.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.liuyb.app.common.cache.MemCached;
import cn.liuyb.app.portal.dao.UserFunctionModelDao;
import cn.liuyb.app.portal.dao.UserRolePermissionDao;
import cn.liuyb.app.portal.domain.UserRolePermission;
import cn.liuyb.app.portal.service.UserRolePermissionService;

@Service
public class UserRolePermissionServiceImpl implements UserRolePermissionService {

	@Autowired
	private UserRolePermissionDao userRolePermissionDao;
	
	@Autowired
	private UserFunctionModelDao userFunctionModelDao;
	
	private static final String MEMCACHED_USERFUNCTION = "UserFunctionModel_";
	
	@Override
	@Transactional
	public void addUserRolePermission(UserRolePermission userRolePermission) {
		userRolePermissionDao.create(userRolePermission);
	}

	@Override
	@Transactional
	public void transAddRolePermission(List<UserRolePermission> list) {
		for (UserRolePermission userRolePermission : list) {
			userRolePermissionDao.create(userRolePermission);
		}
	}

	@Transactional(readOnly = true)
	@Override
	public List<UserRolePermission> listUserRolePermission() {
		return userRolePermissionDao.listAll();
	}

	@Transactional(readOnly = true)
	@Override
	public List<UserRolePermission> findUserRolePermissions(int start, int max) {
		return userRolePermissionDao.findUserRolePermissions(start, max);
	}

	@Transactional(readOnly = true)
	@Override
	public int countAll() {
		return userRolePermissionDao.countAll();
	}

	@Transactional(readOnly = true)
	@Override
	public List<UserRolePermission> findUserRolePermissionsByRoleId(long roleId) {
		return userRolePermissionDao.findUserRolePermissionsByRoleId(roleId);
	}
	
	@Override
	@Transactional
	public void deleteAndInsertPermission(long roleId, String[] ids) {
		for (UserRolePermission userRolePermission : findUserRolePermissionsByRoleId(roleId)) {
			userRolePermissionDao.delete(userRolePermission);
		}
		for (String id : ids) {
			if ("0001".equals(id)) {
				continue;
			}
			UserRolePermission u = new UserRolePermission();
			u.setUserRole(roleId);
			u.setUserFunctionModel(id);
			u.setReadOnly(0);
			userRolePermissionDao.create(u);
		}
	}
	
	@Transactional(readOnly = true)
	@Override
	public void setMemCachedInsertPermission(long roleId){
		MemCached.INSTANCE.set(MEMCACHED_USERFUNCTION+roleId,userFunctionModelDao.listUserFunctionModelByUserRole(roleId));
	}
}
