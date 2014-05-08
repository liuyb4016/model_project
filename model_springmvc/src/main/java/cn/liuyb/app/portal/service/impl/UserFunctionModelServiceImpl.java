package cn.liuyb.app.portal.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.liuyb.app.common.cache.MemCached;
import cn.liuyb.app.portal.dao.UserFunctionModelDao;
import cn.liuyb.app.portal.domain.UserFunctionModel;
import cn.liuyb.app.portal.service.UserFunctionModelService;

@Service
public class UserFunctionModelServiceImpl implements UserFunctionModelService {

	@Autowired
	private UserFunctionModelDao userFunctionModelDao;

	private static final String MEMCACHED_USERFUNCTION = "UserFunctionModel_";

	@Override
	@Transactional
	public void addUserFunctionModel(UserFunctionModel userFunctionModel) {
		userFunctionModelDao.create(userFunctionModel);
	}

	@Transactional(readOnly = true)
	@Override
	public List<UserFunctionModel> listUserFunctionModel() {
		return userFunctionModelDao.listAll();
	}

	@Transactional(readOnly = true)
	@Override
	public List<UserFunctionModel> listUserFunctionModel(int start, int max) {
		return userFunctionModelDao.listUserFunctionModel(start, max);
	}

	@Transactional(readOnly = true)
	@Override
	public int countUserFunctionModelAll() {
		return userFunctionModelDao.countAll();
	}

	@Override
	@Transactional
	public void updateUserFunctionModel(UserFunctionModel userFunctionModel) {
		userFunctionModelDao.update(userFunctionModel);
	}

	@Override
	@Transactional
	public void deleteUserFunctionModel(UserFunctionModel userFunctionModel) {
		userFunctionModelDao.delete(userFunctionModel);
	}

	@Transactional(readOnly = true)
	@Override
	public UserFunctionModel findUserFunctionModel(long id) {
		return userFunctionModelDao.find(id);
	}

	@Transactional(readOnly = true)
	@Override
	public int countUserFuncModelByParentModelId(String parentModelId) {
		Object u =  userFunctionModelDao
				.findUserFuncModelByParentModelId(parentModelId);
		if(u!=null){
			String s = String.valueOf(u);
			if(s.length()<4){
				return 0;
			}
			return Integer.valueOf(s.substring(s.length()-4, s.length()));
		}
		return 0;
	}

	@Override
	public String getNewModelId(String parentModelId) {
		if ("0".equals(parentModelId)) {
			return "0001";
		}
		int count = countUserFuncModelByParentModelId(parentModelId);
		count = count + 1;
		if (count >= 1000) {
			return parentModelId + count;
		} else {
			String countStr = String.valueOf(count);
			int len = countStr.length();
			String zore = "";
			for (int i = 0; i <= (3 - len); i++) {
				zore += "0";
			}
			return parentModelId + zore + countStr;
		}
	}

	@Transactional(readOnly = true)
	@Override
	public List<UserFunctionModel> listUserFunctionModelByModelType(int type) {
		return userFunctionModelDao.listUserFunctionModelByModelType(type);
	}

	@Override
	public Map<String, String> mapUserFuncModel() {
		Map<String, String> map = new HashMap<String, String>();
		for (UserFunctionModel userFunctionModel : listUserFunctionModelByModelType(1)) {
			map.put(userFunctionModel.getModelId(),
					userFunctionModel.getModelName());
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	@Override
	public List<UserFunctionModel> listUserFunctionModelByUserRole(long userRole) {
		List<UserFunctionModel> list = (List<UserFunctionModel>) MemCached.INSTANCE
				.get(MEMCACHED_USERFUNCTION + userRole);
		if (list == null || list.size() == 0) {
			list = userFunctionModelDao
					.listUserFunctionModelByUserRole(userRole);
			MemCached.INSTANCE.set(MEMCACHED_USERFUNCTION + userRole, list);
		}
		return list;
	}

	@Override
	public UserFunctionModel findUserFuncModelByModelUrl(String modelUrl) {
		return userFunctionModelDao.findUserFuncModelByModelUrl(modelUrl);
	}

	@Override
	public UserFunctionModel findUserFuncModelByModelId(String modelId) {
		return userFunctionModelDao.findUserFuncModelByModelId(modelId);
	}

	@Transactional(readOnly = true)
	@Override
	public List<UserFunctionModel> findUserFuncModelByQueryString(String q,
			int start, int max) {
		return userFunctionModelDao.findUserFuncModelByQueryString(q, start, max);
	}

	@Override
	public int countUserFuncModelByQueryString(String q, int start, int max) {
		return userFunctionModelDao.countUserFuncModelByQueryString(q, start, max);
	}


}
