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
	@NamedQuery(name="RoleUrl.findRoleUrlByClassName",
			query="SELECT a FROM RoleUrl a where a.className=:className"),
	@NamedQuery(name="RoleUrl.findRoleUrlGroupByClassName",
			query="SELECT a.className,count(a.className),id FROM RoleUrl a group by a.className"),
	@NamedQuery(name="RoleUrl.findRoleUrlByUrlAndType",
			query="SELECT a FROM RoleUrl a where a.url=:url"),
	@NamedQuery(name="RoleUrl.findRoleUrlByUrlAndTypeReadOnly",
			query="SELECT a FROM RoleUrl a where a.url=:url and a.readOnly=:readOnly"),
	@NamedQuery(name="RoleUrl.findRoleUrlByUserFunctionId",
		query="SELECT a FROM RoleUrl a where a.userFunctionModel=:userFunctionModel"),
	@NamedQuery(name="RoleUrl.findRoleUrlByUserFunctionIdReadOnly",
		query="SELECT a FROM RoleUrl a where a.userFunctionModel=:userFunctionModel and a.readOnly=:readOnly")
	})
public class RoleUrl extends BaseEntity {
	
	private static final long serialVersionUID = 389970463116025388L;
	
	private String className;
	private String url;
	private String urlDesc;
	
	@Column(name="create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;
	@Column(name="update_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;
	
	private Integer readOnly;
	private String userFunctionModel;
	
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

	public String getUserFunctionModel() {
		return userFunctionModel;
	}
	public void setUserFunctionModel(String userFunctionModel) {
		this.userFunctionModel = userFunctionModel;
	}
	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * @return the updateDate
	 */
	public Date getUpdateDate() {
		return updateDate;
	}
	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	/**
	 * @return the className
	 */
	public String getClassName() {
		return className;
	}
	/**
	 * @param className the className to set
	 */
	public void setClassName(String className) {
		this.className = className;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the urlDesc
	 */
	public String getUrlDesc() {
		return urlDesc;
	}
	/**
	 * @param urlDesc the urlDesc to set
	 */
	public void setUrlDesc(String urlDesc) {
		this.urlDesc = urlDesc;
	}
	/**
	 * @return the serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}
