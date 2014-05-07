package cn.liuyb.app.service;

import java.util.List;

import cn.liuyb.app.domain.UserRoleUrl;


public interface UserRoleUrlService {

	public UserRoleUrl findByRoleIdUrl(Long roleId, String url);
	public List<UserRoleUrl> findByRoleId(Long roleId);
	public List<UserRoleUrl> findByUserFunctionId(String userFunctionModel,Long roleId);
	public void add(UserRoleUrl userRoleUrl);
	public void delete(UserRoleUrl userRoleUrl);
	public void updateUserRoleUrl(Long roleId);
	public void deleteAndInsertUserRoleUrl(long roleId, String userFunctionModel,
			String[] ids);
	public List<UserRoleUrl> findByUrl(String url);
	public void update(UserRoleUrl userRoleUrl);
	
}
