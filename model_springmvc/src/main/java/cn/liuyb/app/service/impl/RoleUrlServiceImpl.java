package cn.liuyb.app.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.liuyb.app.dao.RoleUrlDao;
import cn.liuyb.app.domain.RoleUrl;
import cn.liuyb.app.service.RoleUrlService;

@Service
public class RoleUrlServiceImpl implements RoleUrlService{
	
	//private static final Logger logger = Slf4jLogUtils.getLogger(RoleUrlServiceImpl.class);

	@Autowired
	private RoleUrlDao roleUrlDao;
	
	@Transactional
	@Override
	public void add(RoleUrl roleUrl){
		roleUrlDao.create(roleUrl);
	}
	
	@Transactional
	@Override
	public void delete(RoleUrl roleUrl) {
		roleUrlDao.delete(roleUrl);
	}
	
	@Transactional
	@Override
	public void update(RoleUrl roleUrl) {
		roleUrlDao.update(roleUrl);
	}
	
	@Override
	public List<RoleUrl> findRoleUrlByClassName(String className) {
		return roleUrlDao.findRoleUrlByClassName(className);
	}

	@Override
	public List<Object> findRoleUrlGroupByClassName() {
		return roleUrlDao.findRoleUrlGroupByClassName();
	}

	@Override
	public boolean isBeingRoleUrlByUrlAndType(String url) {
		return roleUrlDao.isBeingRoleUrlByUrlAndType(url);
	}

	@Override
	public int countRoleUrlByClassName(String className) {
		return roleUrlDao.countRoleUrlByClassName(className);
	}

	@Override
	public List<RoleUrl> findRoleUrlByClassName(int start, int max,
			String className) {
		return roleUrlDao.findRoleUrlByClassName(start, max, className);
	}

	@Override
	public int countRoleUrlGroupByClassName() {
		return roleUrlDao.countRoleUrlGroupByClassName();
	}

	@Override
	public List<Object> findRoleUrlGroupByClassName(int start, int max) {
		return roleUrlDao.findRoleUrlGroupByClassName(start, max);
	}

	@Override
	public RoleUrl getRoleUrlById(Long id) {
		return roleUrlDao.find(id);
	}

	@Override
	public List<RoleUrl> findRoleUrlByUrlAndTypeReadOnly(String url, Integer readOnly) {
		return roleUrlDao.findRoleUrlByUrlAndTypeReadOnly(url, readOnly);
	}

	@Override
	public boolean isBeingRoleUrlByUrlAndTypeReadOnly(String url,Integer readOnly) {
		return roleUrlDao.isBeingRoleUrlByUrlAndTypeReadOnly(url, readOnly);
	}

	@Override
	public List<RoleUrl> findRoleUrlByUserFunctionModel(String userFunctionModel) {
		return roleUrlDao.findRoleUrlByUserFunctionModel(userFunctionModel);
	}
	
}
