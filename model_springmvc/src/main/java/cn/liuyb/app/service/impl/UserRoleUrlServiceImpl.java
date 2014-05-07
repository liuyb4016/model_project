package cn.liuyb.app.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.liuyb.app.dao.RoleUrlDao;
import cn.liuyb.app.dao.UserRolePermissionDao;
import cn.liuyb.app.dao.UserRoleUrlDao;
import cn.liuyb.app.domain.RoleUrl;
import cn.liuyb.app.domain.UserRolePermission;
import cn.liuyb.app.domain.UserRoleUrl;
import cn.liuyb.app.service.UserRoleUrlService;

@Service
public class UserRoleUrlServiceImpl implements UserRoleUrlService{
	
	//private static final Logger logger = Slf4jLogUtils.getLogger(UserRoleUrlServiceImpl.class);

	@Autowired
	private UserRoleUrlDao userRoleUrlDao;
	@Autowired
	private UserRolePermissionDao userRolePermissionDao;
	@Autowired
	private RoleUrlDao roleUrlDao;
	
	@Override
	public UserRoleUrl findByRoleIdUrl(Long roleId, String url) {
		return userRoleUrlDao.findByRoleIdUrl(roleId, url);
	}

	@Override
	public List<UserRoleUrl> findByRoleId(Long roleId) {
		return userRoleUrlDao.findByRoleId(roleId);
	}

	@Override
	public List<UserRoleUrl> findByUserFunctionId(String userFunctionModel,Long roleId) {
		return userRoleUrlDao.findByUserFunctionId(userFunctionModel,roleId);
	}

	@Transactional
	@Override
	public void add(UserRoleUrl userRoleUrl) {
		userRoleUrlDao.create(userRoleUrl);
	}
	
	@Transactional
	@Override
	public void delete(UserRoleUrl userRoleUrl) {
		userRoleUrlDao.delete(userRoleUrl);
	}
	
	@Transactional
	@Override
	public void updateUserRoleUrl(Long roleId) {
		 List<UserRolePermission> list = userRolePermissionDao.findUserRolePermissionsByRoleId(roleId);
		 for(UserRolePermission userRolePermission:list){
			 String userFunctionModel = userRolePermission.getUserFunctionModel();
			 List<UserRoleUrl> listV = userRoleUrlDao.findByUserFunctionId(userFunctionModel,roleId);
			 //如果这个功能模块已经添加了。那不做新增。需要手动去补充到小权限里去。
			 if(listV==null ||listV.size()==0){
				 List<RoleUrl> listV2 = roleUrlDao.findRoleUrlByUserFunctionModel(userFunctionModel);
				 for(RoleUrl roleUrl:listV2){
					 UserRoleUrl userRoleUrl = new UserRoleUrl();
					 userRoleUrl.setCreateDate(new Date());
					 userRoleUrl.setRoleId(roleId);
					 userRoleUrl.setRoleUrlId(roleUrl.getId());
					 userRoleUrl.setUrl(roleUrl.getUrl());
					 userRoleUrl.setUserFunctionModel(userFunctionModel);
					 userRoleUrlDao.create(userRoleUrl);
				 }
			 }
		 }
	}
	
	@Override
	@Transactional
	public void deleteAndInsertUserRoleUrl(long roleId,String userFunctionModel, String[] ids) {
		for (UserRoleUrl userRoleUrl : this.findByUserFunctionId(userFunctionModel,roleId)) {
			userRoleUrlDao.delete(userRoleUrl);
		}
		for (String id : ids) {
			Long roleUrlId = Long.valueOf(id);
			RoleUrl roleUrl= roleUrlDao.find(roleUrlId);
			UserRoleUrl userRoleUrl = new UserRoleUrl();
			userRoleUrl.setCreateDate(new Date());
			userRoleUrl.setRoleId(roleId);
			userRoleUrl.setRoleUrlId(roleUrlId);
			userRoleUrl.setUrl(roleUrl.getUrl());
			userRoleUrl.setUserFunctionModel(roleUrl.getUserFunctionModel());
			userRoleUrlDao.create(userRoleUrl);
		}
	}

	@Override
	public List<UserRoleUrl> findByUrl(String url) {
		return userRoleUrlDao.findByUrl(url);
	}
	
	@Transactional
	@Override
	public void update(UserRoleUrl userRoleUrl) {
		userRoleUrlDao.update(userRoleUrl);
	}
}
