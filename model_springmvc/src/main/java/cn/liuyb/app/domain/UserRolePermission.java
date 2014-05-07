package cn.liuyb.app.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import cn.liuyb.app.common.domain.BaseEntity;

@Entity
@NamedQueries({ @NamedQuery(name = "UserRolePermission.findUserRolePermission", 
        query = "SELECT u From UserRolePermission u "),
@NamedQuery(name = "UserRolePermission.countUserRolePermission", 
        query = "SELECT COUNT(u) From UserRolePermission u"),
@NamedQuery(name = "UserRolePermission.findUserRolePermissionByRoleId", 
        query = "SELECT u From UserRolePermission u WHERE u.userRole=:q ")
})
public class UserRolePermission extends BaseEntity {

	private static final long serialVersionUID = -2675839827073666599L;
	
	@Column(name="user_role")
	private long userRole;
	@Column(name="user_function_model")
	private String userFunctionModel;
	@Column(name="read_only")
	private Integer readOnly;
	/**
	 * @return the userRole
	 */
	public long getUserRole() {
		return userRole;
	}
	/**
	 * @param userRole the userRole to set
	 */
	public void setUserRole(long userRole) {
		this.userRole = userRole;
	}
	
	/**
	 * @return the userFunctionModel
	 */
	public String getUserFunctionModel() {
		return userFunctionModel;
	}
	/**
	 * @param userFunctionModel the userFunctionModel to set
	 */
	public void setUserFunctionModel(String userFunctionModel) {
		this.userFunctionModel = userFunctionModel;
	}
	/**
	 * @return the readOnly
	 */
	public Integer getReadOnly() {
		return readOnly;
	}
	/**
	 * @param readOnly the readOnly to set
	 */
	public void setReadOnly(Integer readOnly) {
		this.readOnly = readOnly;
	}
			
}
