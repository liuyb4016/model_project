package cn.liuyb.app.portal.dao.jpa;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.liuyb.app.common.dao.AbstractJpaDao;
import cn.liuyb.app.portal.dao.RoleUrlDao;
import cn.liuyb.app.portal.domain.RoleUrl;

@Repository
public class RoleUrlDaoImpl extends AbstractJpaDao<RoleUrl> implements RoleUrlDao{
	
	protected RoleUrlDaoImpl() {
		super(RoleUrl.class);
	}

	@Override
	public List<RoleUrl> findRoleUrlByClassName(String className) {
		return this.findByProperties("RoleUrl.findRoleUrlByClassName", new Object[]{p("className",className)});
	}

	@Override
	public List<Object> findRoleUrlGroupByClassName() {
		return this.findObjectsByProperties("RoleUrl.findRoleUrlGroupByClassName",new Object[]{});
	}

	@Override
	public List<RoleUrl> findRoleUrlByUrlAndType(String url) {
		return this.findByProperties("RoleUrl.findRoleUrlByUrlAndType", new Object[]{p("url",url)});
	}

	@Override
	public boolean isBeingRoleUrlByUrlAndType(String url) {
		List<RoleUrl> list = findRoleUrlByUrlAndType(url);
		if(list!=null &&list.size()>0){
			return true;
		}else{
			return false;
		}
	}
	@Override
	public List<RoleUrl> findRoleUrlByUrlAndTypeReadOnly(String url,Integer readOnly) {
		return this.findByProperties("RoleUrl.findRoleUrlByUrlAndTypeReadOnly",new Object[]{ p("url",url),p("readOnly",readOnly)});
	}
	
	@Override
	public boolean isBeingRoleUrlByUrlAndTypeReadOnly(String url,Integer readOnly) {
		List<RoleUrl> list = findRoleUrlByUrlAndTypeReadOnly(url,readOnly);
		if(list!=null &&list.size()>0){
			return true;
		}else{
			return false;
		}
	}
	
	@Override
	public int countRoleUrlByClassName(String className) {
		return this.findRoleUrlByClassName(className).size();
	}

	@Override
	public List<RoleUrl> findRoleUrlByClassName(int start, int max,
			String className) {
		return this.findByProperties("RoleUrl.findRoleUrlByClassName",start ,max,new Object[]{p("className",className)});
	}

	@Override
	public int countRoleUrlGroupByClassName() {
		return this.findRoleUrlGroupByClassName().size();
	}

	@Override
	public List<Object> findRoleUrlGroupByClassName(int start, int max) {
		return this.findObjectsByProperties("RoleUrl.findRoleUrlGroupByClassName",start ,max,new Object[]{});
	}

	@Override
	public List<RoleUrl> findRoleUrlByUserFunctionModel(String userFunctionModel) {
			return this.findByProperties("RoleUrl.findRoleUrlByUserFunctionId", new Object[]{p("userFunctionModel",userFunctionModel)});
	}
}
