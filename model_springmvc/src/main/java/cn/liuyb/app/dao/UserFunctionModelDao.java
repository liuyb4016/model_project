package cn.liuyb.app.dao;


import java.util.List;

import cn.liuyb.app.common.dao.EntityDao;
import cn.liuyb.app.domain.UserFunctionModel;

public interface UserFunctionModelDao extends EntityDao<UserFunctionModel> {
	public List<UserFunctionModel> listAll();
	public List<UserFunctionModel> listUserFunctionModel(int start,int max);
	public int countAll();
	public Object findUserFuncModelByParentModelId(String parentModelId);
	
	public List<UserFunctionModel> listUserFunctionModelByModelType(int type);
	public List<UserFunctionModel> listUserFunctionModelByUserRole(long userRole);
	
	public UserFunctionModel findUserFuncModelByModelUrl(String modelUrl);
	public UserFunctionModel findUserFuncModelByModelId(String modelId);
	
	public List<UserFunctionModel> findUserFuncModelByQueryString(String q,
			int start, int max);
	public int countUserFuncModelByQueryString(String q,
			int start, int max);
	
}
