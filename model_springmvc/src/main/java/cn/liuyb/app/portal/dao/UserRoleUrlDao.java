package cn.liuyb.app.portal.dao;

import java.util.List;

import cn.liuyb.app.common.dao.EntityDao;
import cn.liuyb.app.portal.domain.UserRoleUrl;

public interface UserRoleUrlDao  extends EntityDao<UserRoleUrl>{

	public UserRoleUrl findByRoleIdUrl(Long roleId, String url);
	List<UserRoleUrl> findByRoleId(Long roleId);
	List<UserRoleUrl> findByUserFunctionId(String userFunctionModel,Long roleId);
	List<UserRoleUrl> findByUrl(String url);
}
