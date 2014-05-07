package cn.liuyb.app.dao;

import java.util.List;

import cn.liuyb.app.common.dao.EntityDao;
import cn.liuyb.app.domain.UserRoleUrl;

public interface UserRoleUrlDao  extends EntityDao<UserRoleUrl>{

	public UserRoleUrl findByRoleIdUrl(Long roleId, String url);
	List<UserRoleUrl> findByRoleId(Long roleId);
	List<UserRoleUrl> findByUserFunctionId(String userFunctionModel,Long roleId);
	List<UserRoleUrl> findByUrl(String url);
}
