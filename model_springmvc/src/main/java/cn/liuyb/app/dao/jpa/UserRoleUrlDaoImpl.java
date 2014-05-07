package cn.liuyb.app.dao.jpa;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.liuyb.app.common.dao.AbstractJpaDao;
import cn.liuyb.app.dao.UserRoleUrlDao;
import cn.liuyb.app.domain.UserRoleUrl;

@Repository
public class UserRoleUrlDaoImpl extends AbstractJpaDao<UserRoleUrl> implements UserRoleUrlDao{

	protected UserRoleUrlDaoImpl() {
		super(UserRoleUrl.class);
	}

	@Override
	public UserRoleUrl findByRoleIdUrl(Long roleId, String url) {
		return this.findByUniqueProperties("UserRoleUrl.findByRoleIdUrl", new Object[]{p("roleId",roleId),p("url",url)});
	}

	@Override
	public List<UserRoleUrl> findByRoleId(Long roleId) {
		return this.findByProperties("UserRoleUrl.findByRoleId",new Object[]{ p("roleId",roleId)});
	}

	@Override
	public List<UserRoleUrl> findByUserFunctionId(String userFunctionModel,Long roleId) {
		return this.findByProperties("UserRoleUrl.findByUserFunctionId", new Object[]{p("userFunctionModel",userFunctionModel), p("roleId",roleId)});
	}

	@Override
	public List<UserRoleUrl> findByUrl(String url) {
		return this.findByProperties("UserRoleUrl.findByUrl", new Object[]{p("url",url)});
	}
	
}
