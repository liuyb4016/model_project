package cn.liuyb.app.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import cn.liuyb.app.common.domain.BaseEntity;


@Entity
@NamedQueries({
	@NamedQuery(name="UserRoleUrl.findByRoleIdUrl",
			query="SELECT a FROM UserRoleUrl a where a.roleId=:roleId AND a.url=:url"),
	@NamedQuery(name="UserRoleUrl.findByRoleId",
			query="SELECT a FROM UserRoleUrl a where a.roleId=:roleId"),
	@NamedQuery(name="UserRoleUrl.findByUserFunctionId",
			query="SELECT a FROM UserRoleUrl a where a.userFunctionModel=:userFunctionModel and a.roleId=:roleId"),
	@NamedQuery(name="UserRoleUrl.findByUrl",
			query="SELECT a FROM UserRoleUrl a where a.url=:url ")
})
public class UserRoleUrl extends BaseEntity {
	
	private static final long serialVersionUID = -8860395822856171783L;
	
	private String url;
	private Long roleUrlId;
	private String userFunctionModel;
	private Long roleId;
	
	@Column(name="create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Long getRoleUrlId() {
		return roleUrlId;
	}
	public void setRoleUrlId(Long roleUrlId) {
		this.roleUrlId = roleUrlId;
	}
	public String getUserFunctionModel() {
		return userFunctionModel;
	}
	public void setUserFunctionModel(String userFunctionModel) {
		this.userFunctionModel = userFunctionModel;
	}
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
}
