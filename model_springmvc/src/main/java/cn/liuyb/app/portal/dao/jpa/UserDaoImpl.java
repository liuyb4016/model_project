package cn.liuyb.app.portal.dao.jpa;


import java.util.List;

import org.springframework.stereotype.Repository;

import cn.liuyb.app.common.dao.AbstractFlushModeCommitDao;
import cn.liuyb.app.portal.dao.UserDao;
import cn.liuyb.app.portal.domain.User;

@Repository
public class UserDaoImpl extends AbstractFlushModeCommitDao<User> implements UserDao {

	public UserDaoImpl() {
		super(User.class);
	}

	@Override
	public List<User> findByUsername(int start, int max, String name) {
		return this.findByProperties("User.findByUsername", start, max, new Object[]{p("name",name), p("username",name)});
	}

	@Override
	public int countByUsername(String name) {
		return this.countByProperties("User.countByUsername", new Object[]{p("name",name), p("username",name)});
	}

	@Override
	public User loginUser(String username, String password) {
		return this.findByUniqueProperties("User.loginUser", new Object[]{p("username",username), p("password",password)});
	}

	@Override
	public List<User> findByUsernameNotSelf(String username, Long id) {
		if(id>0){
			return this.findByProperties("User.findByUsernameNotSelf",new Object[]{p("id",id), p("username",username)});
		}else{
			return this.findByProperties("User.findByUsernameV", new Object[]{p("username",username)});
		}
	}
}
