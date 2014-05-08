package cn.liuyb.app.portal.dao;


import java.util.List;

import cn.liuyb.app.common.dao.EntityDao;
import cn.liuyb.app.portal.domain.User;

public interface UserDao extends EntityDao<User> {
	
	public List<User> findByUsername(int start,int max,String name);
	public int countByUsername(String name);
	public User loginUser(String username, String password);
	public List<User> findByUsernameNotSelf(String username,Long id);
}
