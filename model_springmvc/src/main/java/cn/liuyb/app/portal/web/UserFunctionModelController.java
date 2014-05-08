package cn.liuyb.app.portal.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import cn.liuyb.app.portal.domain.UserFunctionModel;
import cn.liuyb.app.portal.service.UserFunctionModelService;
import cn.liuyb.app.portal.service.impl.CurrentUser;

@Controller
@RequestMapping("/admin/userfuncmodel")
public class UserFunctionModelController implements NeedLoginController{

	private static final Logger logger = Slf4jLogUtils.getLogger(UserFunctionModelController.class);
	@Autowired
	private UserFunctionModelService userFunctionModelService;
	
	@MethodDirections(value = "查询：进入模块管理")
	@RequestMapping(method=RequestMethod.GET)
	public String list() {
		return "admin/userfuncmodel_list";
	}

	@MethodDirections(value = "查询：获得模块列表")
	@RequestMapping(value="/page/{step}/{page}", method = RequestMethod.GET)
    public @ResponseBody Object[] getPageAndCount(@PathVariable("page") int page, 
    		@PathVariable("step") int step, HttpServletResponse response) throws IllegalAccessException {

        int start = PaginationUtils.computeStartPosition(page, step);
        
        List<UserFunctionModel> listUserFunctionModels = userFunctionModelService.listUserFunctionModel(start, step);
        if(listUserFunctionModels.isEmpty()){
        	listUserFunctionModels = new ArrayList<UserFunctionModel>();	
        }
        logger.debug("UserRole {},{}", start, start + listUserFunctionModels.size());
        
        int size = userFunctionModelService.countUserFunctionModelAll();
        int pageCount = PaginationUtils.computeTotalPage(size, step);

        Map<String,String> map = userFunctionModelService.mapUserFuncModel();
        return new Object[]{pageCount, listUserFunctionModels,map};
    }
	
	 @MethodDirections(value = "查询：获得模块列表查询结果")
		@RequestMapping(value="/query/{step}/{page}", method = RequestMethod.GET)
	    public @ResponseBody Object[] queryPageAndCount(@PathVariable("page") int page, 
	    		@PathVariable("step") int step, @RequestParam String q) throws IllegalAccessException {
			
	    	q = "%"+q+"%";
			logger.debug("UserRole queryString {}", q);
	        
	        int start = PaginationUtils.computeStartPosition(page, step);
	        
	        List<UserFunctionModel> listUserFunctionModels = userFunctionModelService.findUserFuncModelByQueryString(q, start, step);
	        int size = userFunctionModelService.countUserFuncModelByQueryString(q, start, step);

	        logger.debug("UserRole {},{}", start, start + listUserFunctionModels.size());

	        int pageCount = PaginationUtils.computeTotalPage(size, step);
	        Map<String,String> map = userFunctionModelService.mapUserFuncModel();
	        return new Object[]{pageCount, listUserFunctionModels,map};        
	    }
	
	@MethodDirections(value = "修改：添加或更新模块信息")
	@RequestMapping(value="/addOrUpdateUserFuncModel")
	public @ResponseBody boolean addOrUpdateUserFuncModel(@RequestParam("modelName")String modelName,
			@RequestParam("description")String description,@RequestParam("id")long id,
			@RequestParam("parentModelId")String parentModelId,@RequestParam("modelType")int modelType,
			@RequestParam("modelUrl")String modelUrl) throws IllegalAccessException {
		Long userId = CurrentUser.getUserId();
		if(userId==null){
			return false;
		}
		UserFunctionModel userFunctionModel = null;
		if(id==-1){
			userFunctionModel = new UserFunctionModel();
		}else{
			userFunctionModel = userFunctionModelService.findUserFunctionModel(id);
			if(userFunctionModel==null){
				return false;
			}
		}
		parentModelId = (parentModelId==null||"null".equals(parentModelId))?"0":parentModelId;
		userFunctionModel.setModelName(modelName);
		userFunctionModel.setDescription(description);
		userFunctionModel.setModelUrl(modelUrl);
		try {
			if(id==-1){
				String modelId = userFunctionModelService.getNewModelId(parentModelId);
				userFunctionModel.setModelId(modelId);
				userFunctionModel.setModelOrder(modelId);
				userFunctionModel.setModelType(modelType);
				userFunctionModel.setParentModelId(parentModelId);
				userFunctionModelService.addUserFunctionModel(userFunctionModel);
			}else{
				userFunctionModelService.updateUserFunctionModel(userFunctionModel);
			}
		} catch (EntityExistsException e) {
			return false;
		}
		
		return true;
	}
	
	@MethodDirections(value = "修改：进入更新模块信息")
	@RequestMapping(value="/toUpdateUserFuncModel/{id}",method = RequestMethod.GET)
	public @ResponseBody Object[] toUpdateUserFuncModel(@PathVariable("id")Long id) throws IllegalAccessException {
		Long userId = CurrentUser.getUserId();
		if(userId==null){
			return new Object[]{new UserFunctionModel(),new ArrayList<UserFunctionModel>()};
		}
		UserFunctionModel userFunctionModel = null;
		List<UserFunctionModel> userFunctionModels = null;
		try {
			if(id!=-1){
				userFunctionModel = userFunctionModelService.findUserFunctionModel(id);	
			}
			userFunctionModels = userFunctionModelService.listUserFunctionModelByModelType(1);
		} catch (EntityExistsException e) {
			return new Object[]{new UserFunctionModel(),new ArrayList<UserFunctionModel>()};
		}
		if(userFunctionModel==null){
			userFunctionModel = new UserFunctionModel();
		}
		return new Object[]{userFunctionModel,userFunctionModels};
	}
	
	@MethodDirections(value = "修改：删除模块信息")
	@RequestMapping(value="/deleteUserFuncModel/{id}",method = RequestMethod.GET)
	public @ResponseBody int deleteUserFuncModel(@PathVariable("id")Long id) throws IllegalAccessException {
		Long userId = CurrentUser.getUserId();
		if(userId==null){
			return -1;
		}
		if(id == null || id<0){
			return -2;	
		}
		UserFunctionModel userFunctionModel = userFunctionModelService.findUserFunctionModel(id);
		if(userFunctionModel==null){
			return -3;
		}
		if(userFunctionModelService.countUserFuncModelByParentModelId(userFunctionModel.getModelId())>0){
			return -4;
		}
		try {
			userFunctionModelService.deleteUserFunctionModel(userFunctionModel);
		}catch (EntityExistsException e) {
			return -5;
		}
		return 1;
	}
	
}
