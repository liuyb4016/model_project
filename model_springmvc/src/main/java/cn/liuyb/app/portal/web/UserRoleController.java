package cn.liuyb.app.portal.web;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.liuyb.app.common.utils.MethodDirections;
import cn.liuyb.app.common.utils.PaginationUtils;
import cn.liuyb.app.common.utils.Slf4jLogUtils;
import cn.liuyb.app.portal.domain.UserRole;
import cn.liuyb.app.portal.service.UserRoleService;
import cn.liuyb.app.portal.service.impl.CurrentUser;

@Controller
@RequestMapping("/admin/userrole")
public class UserRoleController implements NeedLoginController{

	private static final Logger logger = Slf4jLogUtils.getLogger(UserRoleController.class);
	@Autowired
	private UserRoleService userRoleService;
	
	@MethodDirections(value = "查询：进入角色管理")
	@RequestMapping(method=RequestMethod.GET)
	public String list() {
		return "admin/userRole_list";
	}
	
	@MethodDirections(value = "查询：获得角色列表")
	@RequestMapping(value="/page/{step}/{page}", method = RequestMethod.GET)
    public @ResponseBody Object[] getPageAndCount(@PathVariable("page") int page, 
    		@PathVariable("step") int step, HttpServletResponse response) throws IllegalAccessException {

        int start = PaginationUtils.computeStartPosition(page, step);
        
        List<UserRole> listUserRoles = userRoleService.findUserRoles(start, step);
        if(listUserRoles.isEmpty()){
        	listUserRoles = new ArrayList<UserRole>();	
        }
        logger.debug("UserRole {},{}", start, start + listUserRoles.size());
        
        int size = userRoleService.countUserRoles();
        int pageCount = PaginationUtils.computeTotalPage(size, step);

        return new Object[]{pageCount, listUserRoles};
    }
	
	@MethodDirections(value = "修改：添加或更新角色信息")
	@RequestMapping(value="/addOrUpdateUserRole")
	public @ResponseBody boolean addOrUpdateUserRole(@RequestParam("roleName")String roleName,
			@RequestParam("description")String description,@RequestParam("id")Long id) throws IllegalAccessException {
		Long userId = CurrentUser.getUserId();
		if(userId==null){
			return false;
		}
		UserRole userRole = null;
		if(id==-1){
			userRole = new UserRole();
		}else{
			userRole = userRoleService.findUserRole(id);
			if(userRole==null){
				return false;
			}
		}
		userRole.setRoleName(roleName);
		userRole.setDescription(description);
		try {
			if(id==-1){
				userRole.setRoleLevel(1);
				userRoleService.addUserRole(userRole);
			}else{
				userRoleService.updateUserRole(userRole);
			}
		} catch (EntityExistsException e) {
			return false;
		}
		
		return true;
	}
	
	@MethodDirections(value = "修改：进入更新角色信息")
	@RequestMapping(value="/toUpdateUserRole/{id}",method = RequestMethod.GET)
	public @ResponseBody UserRole toUpdateUserRole(@PathVariable("id")Long id) throws IllegalAccessException {
		Long userId = CurrentUser.getUserId();
		if(userId==null){
			return new UserRole();
		}
		UserRole userRole = null;
		try {
			userRole = userRoleService.findUserRole(id);
		} catch (EntityExistsException e) {
			return new UserRole();
		}
		if(userRole==null){
			return new UserRole();
		}
		return userRole;
	}
	
	@MethodDirections(value = "修改：删除角色信息")
	@RequestMapping(value="/deleteUserRole/{id}",method = RequestMethod.GET)
	public @ResponseBody int deleteUserRole(@PathVariable("id")Long id) throws IllegalAccessException {
		Long userId = CurrentUser.getUserId();
		if(userId==null){
			return -1;
		}
		if(id == null || id<0){
			return -2;	
		}
		UserRole userRole = userRoleService.findUserRole(id);
		if(userRole==null){
			return -3;
		}
		if(userRole.getUserRolePermissionsSize()>0){
			return -4;
		}
		try {
			userRoleService.deleteUserRole(userRole);
		}catch (EntityExistsException e) {
			return -5;
		}
		return 1;
	}
	
}
