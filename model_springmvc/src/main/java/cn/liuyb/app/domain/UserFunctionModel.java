package cn.liuyb.app.domain;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Transient;

import cn.liuyb.app.common.domain.BaseEntity;

@Entity
@NamedQueries({
		@NamedQuery(name = "UserFunctionModel.findUserFunctionModel", query = "SELECT u From UserFunctionModel u order by u.modelId asc"),
		@NamedQuery(name = "UserFunctionModel.countUserFunctionModel", query = "SELECT COUNT(u) From UserFunctionModel u"),
		@NamedQuery(name = "UserFunctionModel.findUserFuncModelByParentModelId", query = "SELECT MAX(u.modelId) From UserFunctionModel u WHERE u.parentModelId=:q"),
		@NamedQuery(name = "UserFunctionModel.findUserFuncModelByModelType", query = "SELECT u From UserFunctionModel u WHERE u.modelType=:q  order by u.modelId asc"),
		@NamedQuery(name = "UserRolePermission.findUserFuncModelByRoleId", 
		query = "SELECT ur From  UserFunctionModel ur,UserRolePermission u  where u.userFunctionModel=ur.modelId and u.userRole = :userRole ORDER BY ur.modelId"),
		@NamedQuery(name = "UserFunctionModel.findUserFuncModelByModelUrl", query = "SELECT u From UserFunctionModel u WHERE u.modelUrl=:modelUrl"),
		@NamedQuery(name = "UserFunctionModel.findUserFuncModelByModelId", query = "SELECT u From UserFunctionModel u WHERE u.modelId=:modelId"),
		@NamedQuery(name = "UserFunctionModel.findUserFuncModelByUserRole", 
		query = "SELECT u FROM UserFunctionModel u WHERE u.modelId " +
				"IN(SELECT up.userFunctionModel FROM UserRolePermission up WHERE up.userRole=:userRole) AND u.modelUrl !=''"),
		@NamedQuery(name = "UserFunctionModel.findUserFuncModelByQueryString", query = "SELECT u FROM UserFunctionModel u WHERE (u.modelName LIKE :q)"),
		@NamedQuery(name = "UserFunctionModel.countUserFuncModelByQueryString", query = "SELECT COUNT(u) FROM UserFunctionModel u WHERE (u.modelName LIKE :q)"),
})
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE) 
public class UserFunctionModel extends BaseEntity {


	private static final long serialVersionUID = 3329137001870525817L;
	@Column(name = "model_id")
	private String modelId;
	@Column(name = "model_name")
	private String modelName;
	@Column(name = "description")
	private String description;
	@Column(name = "parent_model_id")
	private String parentModelId;
	@Column(name = "model_type")
	private Integer modelType;
	@Column(name = "model_url")
	private String modelUrl;
	@Column(name = "model_order")
	private String modelOrder;

	@Transient
	private List<UserFunctionModel> userFunctionModels;

	/**
	 * @return the userFunctionModels
	 */
	public List<UserFunctionModel> getUserFunctionModels() {
		return userFunctionModels;
	}

	/**
	 * @param userFunctionModels the userFunctionModels to set
	 */
	public void setUserFunctionModels(List<UserFunctionModel> userFunctionModels) {
		this.userFunctionModels = userFunctionModels;
	}

	/**
	 * @return the modelId
	 */
	public String getModelId() {
		return modelId;
	}

	/**
	 * @param modelId
	 *            the modelId to set
	 */
	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	/**
	 * @return the modelName
	 */
	public String getModelName() {
		return modelName;
	}

	/**
	 * @param modelName
	 *            the modelName to set
	 */
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description
	 *            the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the parentModelId
	 */
	public String getParentModelId() {
		return parentModelId;
	}

	/**
	 * @param parentModelId
	 *            the parentModelId to set
	 */
	public void setParentModelId(String parentModelId) {
		this.parentModelId = parentModelId;
	}

	/**
	 * @return the modelType
	 */
	public Integer getModelType() {
		return modelType;
	}

	/**
	 * @param modelType
	 *            the modelType to set
	 */
	public void setModelType(Integer modelType) {
		this.modelType = modelType;
	}

	/**
	 * @return the modelUrl
	 */
	public String getModelUrl() {
		return modelUrl;
	}

	/**
	 * @param modelUrl
	 *            the modelUrl to set
	 */
	public void setModelUrl(String modelUrl) {
		this.modelUrl = modelUrl;
	}

	/**
	 * @return the modelOrder
	 */
	public String getModelOrder() {
		return modelOrder;
	}

	/**
	 * @param modelOrder the modelOrder to set
	 */
	public void setModelOrder(String modelOrder) {
		this.modelOrder = modelOrder;
	}


}
