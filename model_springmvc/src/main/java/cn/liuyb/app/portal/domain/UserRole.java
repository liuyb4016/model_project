package cn.liuyb.app.portal.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

import cn.liuyb.app.common.domain.BaseEntity;

@Entity
@NamedQueries({
		@NamedQuery(name = "UserRole.findUserRole", query = "SELECT u From UserRole u "),
		@NamedQuery(name = "UserRole.countUserRole", query = "SELECT COUNT(u) From UserRole u") })
public class UserRole extends BaseEntity {

	private static final long serialVersionUID = 8934672466332606301L;
	@Column(name = "role_name")
	private String roleName;
	@Column(name = "description")
	private String description;
	@Column(name = "role_level")
	private Integer roleLevel;
	@Column(name = "role_type")
	private Integer roleType;
	
	@OneToMany(mappedBy="userRole")
	private List<UserRolePermission> userRolePermissions;
	
	/**
	 * @return the roleType
	 */
	public Integer getRoleType() {
		return roleType;
	}
	/**
	 * @param roleType the roleType to set
	 */
	public void setRoleType(Integer roleType) {
		this.roleType = roleType;
	}
	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}
	/**
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * @return the roleLevel
	 */
	public Integer getRoleLevel() {
		return roleLevel;
	}
	/**
	 * @param roleLevel the roleLevel to set
	 */
	public void setRoleLevel(Integer roleLevel) {
		this.roleLevel = roleLevel;
	}
	
	/**
	 * @return the userRolePermissions
	 */
	public List<UserRolePermission> getUserRolePermissions() {
		return userRolePermissions;
	}
	/**
	 * @param userRolePermissions the userRolePermissions to set
	 */
	public void setUserRolePermissions(List<UserRolePermission> userRolePermissions) {
		this.userRolePermissions = userRolePermissions;
	}
	/**
	 * @return the users
	 */
	public int getUserRolePermissionsSize() {
		return userRolePermissions.size();
	}
	
}
