package cn.liuyb.app.service;

import java.util.List;
import java.util.Map;

import cn.liuyb.app.domain.UserFunctionModel;

public interface UserFunctionModelService {

	public void addUserFunctionModel(UserFunctionModel userFunctionModel);
	public List<UserFunctionModel> listUserFunctionModel();
	
	public UserFunctionModel findUserFunctionModel(long id);
	
	public int countUserFuncModelByParentModelId(String parentModelId);
	public String getNewModelId(String parentModelId);
	
	public List<UserFunctionModel> listUserFunctionModel(int start,int max);
	public int countUserFunctionModelAll();
	
	public void updateUserFunctionModel(UserFunctionModel userFunctionModel);
	public void deleteUserFunctionModel(UserFunctionModel userFunctionModel);
	
	public List<UserFunctionModel> listUserFunctionModelByModelType(int type);
	
	public Map<String,String> mapUserFuncModel();
	
	public List<UserFunctionModel> listUserFunctionModelByUserRole(long userRole);
	public UserFunctionModel findUserFuncModelByModelUrl(String modelUrl);	
	public UserFunctionModel findUserFuncModelByModelId(String modelId);
	
	public List<UserFunctionModel> findUserFuncModelByQueryString(String q,
			int start, int max);
	
	public int countUserFuncModelByQueryString(String q,
			int start, int max);
}
