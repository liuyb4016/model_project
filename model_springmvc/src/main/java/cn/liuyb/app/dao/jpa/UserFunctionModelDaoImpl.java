package cn.liuyb.app.dao.jpa;


import java.util.List;

import org.springframework.stereotype.Repository;

import cn.liuyb.app.common.dao.AbstractFlushModeCommitDao;
import cn.liuyb.app.dao.UserFunctionModelDao;
import cn.liuyb.app.domain.UserFunctionModel;

@Repository
public class UserFunctionModelDaoImpl extends
		AbstractFlushModeCommitDao<UserFunctionModel> implements
		UserFunctionModelDao {

	public UserFunctionModelDaoImpl() {
		super(UserFunctionModel.class);
	}

	@Override
	public List<UserFunctionModel> listAll() {
		return this.findByProperties("UserFunctionModel.findUserFunctionModel",new Object[]{});
	}

	@Override
	public List<UserFunctionModel> listUserFunctionModel(int start,int max) {
		return this.findByProperties("UserFunctionModel.findUserFunctionModel", start, max,new Object[]{});
	}

	@Override
	public int countAll() {
		return this.countByProperties("UserFunctionModel.countUserFunctionModel",new Object[]{});
	}

	@Override
	public Object findUserFuncModelByParentModelId(String parentModelId) {
		return this.findObjectsByProperties("UserFunctionModel.findUserFuncModelByParentModelId",new Object[]{ p("q",parentModelId)}).get(0);
	}

	@Override
	public List<UserFunctionModel> listUserFunctionModelByModelType(int type) {
		return this.findByProperties("UserFunctionModel.findUserFuncModelByModelType", new Object[]{p("q",type)});
	}

	@Override
	public List<UserFunctionModel> listUserFunctionModelByUserRole(long userRole) {
		return this.findByProperties("UserRolePermission.findUserFuncModelByRoleId", new Object[]{p("userRole",userRole)});
	}

	@Override
	public UserFunctionModel findUserFuncModelByModelUrl(String modelUrl) {
		return this.findByUniqueProperties("UserFunctionModel.findUserFuncModelByModelUrl", new Object[]{p("modelUrl",modelUrl)});
	}
	@Override
	public UserFunctionModel findUserFuncModelByModelId(String modelId) {
		return this.findByUniqueProperties("UserFunctionModel.findUserFuncModelByModelId", new Object[]{p("modelId",modelId)});
	}

	@Override
	public List<UserFunctionModel> findUserFuncModelByQueryString(String q,
			int start, int max) {
		return findByProperties("UserFunctionModel.findUserFuncModelByQueryString", start, max,
				new Object[]{p("q", q)});
	}

	@Override
	public int countUserFuncModelByQueryString(String q, int start, int max) {
		return countByProperties("UserFunctionModel.countUserFuncModelByQueryString",new Object[]{p("q", q)});
	}
	
}
