package cn.liuyb.app.portal.web;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.liuyb.app.common.utils.MethodDirections;
import cn.liuyb.app.common.utils.Slf4jLogUtils;
import cn.liuyb.app.portal.domain.RoleUrl;
import cn.liuyb.app.portal.domain.UserFunctionModel;
import cn.liuyb.app.portal.domain.UserRole;
import cn.liuyb.app.portal.domain.UserRolePermission;
import cn.liuyb.app.portal.domain.UserRoleUrl;
import cn.liuyb.app.portal.service.RoleUrlService;
import cn.liuyb.app.portal.service.UserFunctionModelService;
import cn.liuyb.app.portal.service.UserRolePermissionService;
import cn.liuyb.app.portal.service.UserRoleService;
import cn.liuyb.app.portal.service.UserRoleUrlService;
import cn.liuyb.app.portal.service.UserService;
import cn.liuyb.app.portal.service.impl.CurrentUser;

@Controller
@RequestMapping("/admin/userrolepermission")
public class UserRolePermissionController implements NeedLoginController{

	private static final Logger logger = Slf4jLogUtils.getLogger(UserRolePermissionController.class);
	@Autowired
	private UserFunctionModelService userFunctionModelService;
	@Autowired
	private UserRolePermissionService userRolePermissionService;
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private UserService userService;
	@Autowired
	private UserRoleUrlService userRoleUrlService;
	@Autowired
	private RoleUrlService roleUrlService;
	
	@MethodDirections(value = "查询：获得角色权限列表")
	@RequestMapping(value="/listRolePermission/{id}", method = RequestMethod.GET)
    public String listRolePermission(@PathVariable("id")long id,HttpServletResponse response,Model model) throws IllegalAccessException {
		List<UserFunctionModel> listUserFuncModel = userFunctionModelService.listUserFunctionModel();
		logger.debug("UserFunctionModel {},{}",+ listUserFuncModel.size());
		List<UserRolePermission> listUserRolePermission = userRolePermissionService.findUserRolePermissionsByRoleId(id);
		logger.debug("UserRolePermission {},{}",+ listUserRolePermission.size());
		UserRole userRole = userRoleService.findUserRole(id);
		
		model.addAttribute("listUserFuncModel", listUserFuncModel);
		model.addAttribute("listUserRolePermission",listUserRolePermission);
		model.addAttribute("userRole",userRole);
		return "admin/modifyrolepermission";
    }
	
	@MethodDirections(value = "修改：添加或更新角色权限")
	@RequestMapping(value="/addOrUpdateRolePermission",method = RequestMethod.POST)
	public @ResponseBody int addOrUpdateRolePermission(@RequestParam("roleId")Long roleId,
			@RequestParam("ids[]")String[] ids) throws IllegalAccessException {
		// 修改权限树
		Long userId = CurrentUser.getUserId();
		if(userId==null){
			return -1;
		}
		if(roleId==null || roleId<0){
			return -2;	
		}
		try{
			userRolePermissionService.deleteAndInsertPermission(roleId, ids);
			userRolePermissionService.setMemCachedInsertPermission(roleId);
			userRoleUrlService.updateUserRoleUrl(roleId);
		}catch(Exception e){
			return -3;
		}
		return 1;
	}
	
	@MethodDirections(value = "修改：获得角色权限列表")
	@RequestMapping(value="/getUserRolePermission")
	public @ResponseBody Object[] getUserRolePermission(HttpServletResponse response) throws IllegalAccessException {
		Long userId = CurrentUser.getUserId();
		if(userId==null){
			return new Object[]{new ArrayList<UserRolePermission>()};
		}
		long roleId = userService.getUserRoleByUserId(userId);
		List<UserFunctionModel> userFunctionModels = userFunctionModelService.listUserFunctionModelByUserRole(roleId);
		
		List<UserFunctionModel> userFunctionStepOne = getUserFunctionModels(userFunctionModels,"0001");
		for(UserFunctionModel userFunctionModel : userFunctionStepOne){
			String modelId=  userFunctionModel.getModelId();
			List<UserFunctionModel> userFunctionStepTwo = getUserFunctionModels(userFunctionModels,modelId);
			userFunctionModel.setUserFunctionModels(userFunctionStepTwo);
		}
		return new Object[]{userFunctionStepOne};
	}
	
	private List<UserFunctionModel>  getUserFunctionModels(List<UserFunctionModel> sources,String modelCode){
		List<UserFunctionModel> list = new ArrayList<UserFunctionModel>();
		for(UserFunctionModel userFunctionModel : sources){
			if(modelCode.equals(userFunctionModel.getParentModelId())){
				list.add(userFunctionModel);
			}
		}
		return list;
	}
	
	@MethodDirections(value = "修改：进入更新角色权限")
	@RequestMapping(value="/toupdateuserroleurl/{userFunctionModel}/{roleId}", method = RequestMethod.GET)
    public String toUpdateUserRoleUrl(@PathVariable("roleId") Long roleId,@PathVariable("userFunctionModel") String userFunctionModel
    		,HttpServletResponse response,Model model) throws IllegalAccessException {
		List<RoleUrl> listRoleUrl = roleUrlService.findRoleUrlByUserFunctionModel(userFunctionModel);
		List<UserRoleUrl> listUserRoleUrl = userRoleUrlService.findByUserFunctionId(userFunctionModel,roleId);
		UserFunctionModel userFunctionModelV = userFunctionModelService.findUserFuncModelByModelId(userFunctionModel);
		model.addAttribute("roleId",roleId);
		model.addAttribute("userFunctionModel",userFunctionModelV);
		model.addAttribute("listRoleUrl",listRoleUrl);
		model.addAttribute("listUserRoleUrl",listUserRoleUrl);
		return "admin/modifyuserroleurl";
    }
	
	@MethodDirections(value = "修改：更新角色Url小权限")
	@RequestMapping(value="/addOrUpdateUserRoleUrl", method = RequestMethod.POST)
	public @ResponseBody int addOrUpdateUserRoleUrl(@RequestParam("roleId")Long roleId,@RequestParam("userFunctionModel") String userFunctionModel,
			@RequestParam("ids[]")String[] ids) throws IllegalAccessException {
		// 修改小权限树
		Long userId = CurrentUser.getUserId();
		if(userId==null){
			return -1;
		}
		if(roleId==null || roleId<0){
			return -2;	
		}
		try{
			userRoleUrlService.deleteAndInsertUserRoleUrl(roleId, userFunctionModel, ids);
		}catch(Exception e){
			return -3;
		}
		return 1;
	}
}
