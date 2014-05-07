package cn.liuyb.app.web;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.liuyb.app.common.utils.MethodDirections;
import cn.liuyb.app.common.utils.PaginationUtils;
import cn.liuyb.app.common.utils.ReflectClassUtils;
import cn.liuyb.app.common.utils.Slf4jLogUtils;
import cn.liuyb.app.domain.RoleUrl;
import cn.liuyb.app.domain.UserFunctionModel;
import cn.liuyb.app.domain.UserRoleUrl;
import cn.liuyb.app.service.RoleUrlService;
import cn.liuyb.app.service.UserFunctionModelService;
import cn.liuyb.app.service.UserRoleUrlService;

@Controller
@RequestMapping("/admin/roleurl")
public class RoleUrlController implements NeedLoginController {
	
	private static final Logger logger = Slf4jLogUtils.getLogger(RoleUrlController.class);
	
	
	@Autowired
	private RoleUrlService roleUrlService;
	@Autowired
	private UserFunctionModelService userFunctionModelService;
	@Autowired
	private UserRoleUrlService userRoleUrlService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(
				dateFormat, false));
	}
	
	@MethodDirections(value = "查询：进入角色url")
	@RequestMapping(method=RequestMethod.GET)
	public String list() {
		return "admin/roleurl_list";
	}
	
	@MethodDirections(value = "查询：获得角色url")
	@RequestMapping(value="/page/{step}/{page}/1", method = RequestMethod.GET)
    public @ResponseBody Object[] getPageAndCount(@PathVariable("page") int page,
			@PathVariable("step") int step) {
        int start = PaginationUtils.computeStartPosition(page, step);
        
		List<Object> roleUrls = roleUrlService.findRoleUrlGroupByClassName(start, step);

        logger.debug("roleUrls {},{}", start, start + roleUrls.size());
        
        int size = roleUrlService.countRoleUrlGroupByClassName();
        int pageCount = PaginationUtils.computeTotalPage(size, step);

        return new Object[]{pageCount, roleUrls};
    }
	
	@MethodDirections(value = "查询：进入按Id查看角色url")
	@RequestMapping(value = "/showRoleUrl/{roleurlId}")
	public String showRoleUrl(@PathVariable("roleurlId") String roleurlId,Model model) {
		model.addAttribute("roleurlId", roleurlId);
		return "admin/showRoleUrlList";
	}
	
	@MethodDirections(value = "查询：按Id查看角色url")
	@RequestMapping(value = "page/{step}/{page}/showRoleUrl/{roleurlId}")
	public @ResponseBody Object[] getAppDownUser(@PathVariable("page") int page,@PathVariable("step") int step,
			@PathVariable("roleurlId") String roleurlId) {
		int start = PaginationUtils.computeStartPosition(page, step);
		//RoleUrl roleUrl = roleUrlService.getRoleUrlById(roleurlId);
		String clazzName = roleurlId;//roleUrl.getClassName();
		List<RoleUrl> roleurls = roleUrlService.findRoleUrlByClassName(start, step, clazzName);
		int size = roleUrlService.countRoleUrlByClassName(clazzName);
		int pageCount = PaginationUtils.computeTotalPage(size, step);
		return new Object[]{pageCount,roleurls};
	}
	
	@MethodDirections(value = "修改：更新角色url的信息")
	@RequestMapping(value = "/updateurldesc")
	public @ResponseBody int updateurldesc(@RequestParam("urlDesc") String urldesc,@RequestParam("url") String url,@RequestParam("id") Long id) {
		RoleUrl roleUrl = roleUrlService.getRoleUrlById(id);
		String urlV = roleUrl.getUrl();
		roleUrl.setUpdateDate(new Date());
		roleUrl.setUrlDesc(urldesc);
		roleUrl.setUrl(url);
		roleUrlService.update(roleUrl);
		if(url!=null && !url.equals(urlV)){
			List<UserRoleUrl> list = userRoleUrlService.findByUrl(urlV);
			for(UserRoleUrl userRoleUrl:list){
				userRoleUrl.setUrl(url);
				userRoleUrlService.update(userRoleUrl);
			}
		}
		return 1;
	}
	
	@MethodDirections(value = "修改：刷新角色url数据")
	@SuppressWarnings("rawtypes")
	@RequestMapping(method=RequestMethod.GET,value="/updateRoleUrl")
	public @ResponseBody int  updateRoleUrl() throws ClassNotFoundException {
		List<String> listPackageNames = new ArrayList<String>();
		listPackageNames.add("cn.com.robusoft.mdesk.admin.controller");
		for(String packageName:listPackageNames){
			Set<Class<?>> classes = ReflectClassUtils.getClasses(packageName);
			Iterator i = classes.iterator();// 先迭代出来
			while (i.hasNext()) {// 遍历
				Class clazz = (Class) i.next();
				String className = clazz.getName();
				if(!clazz.isInterface() && !className.contains("$")){
					String portal = "portal";
					
					List<String> list = ReflectClassUtils.getAnnoRequestMappingValue(portal, className);
					String headerUrl = ReflectClassUtils.getAnnoRequestMappingHeaderValue(portal, className);
					if(list!=null){
						UserFunctionModel userFunctionModel = userFunctionModelService.findUserFuncModelByModelUrl(headerUrl);
						for (String str : list) {
							String[] strs = str.split("σ");
							String url = strs[0];
							String urlDesc = strs.length>=2?strs[1]:"";
							//按照url判断是否存在，如果不存在就新增一条记录。
							if(!roleUrlService.isBeingRoleUrlByUrlAndType(url)){
								RoleUrl roleUrl = new RoleUrl();
								roleUrl.setClassName(clazz.getSimpleName());
								roleUrl.setUrl(url);
								roleUrl.setUrlDesc(urlDesc);
								roleUrl.setCreateDate(new Date());
								roleUrl.setUpdateDate(new Date());
								if(userFunctionModel!=null){
									roleUrl.setUserFunctionModel(userFunctionModel.getModelId());
								}
								roleUrlService.add(roleUrl);
							}
						}
					}
				}
			}
		}
		return 1;
	}
	
	@MethodDirections(value = "修改：删除角色url的信息")
	@RequestMapping(value = "/deleteUrl")
	public @ResponseBody int deleteUrl(@RequestParam("url") String url,@RequestParam("id") Long id) {
		RoleUrl roleUrl = roleUrlService.getRoleUrlById(id);
		roleUrlService.delete(roleUrl);
		List<UserRoleUrl> list = userRoleUrlService.findByUrl(url);
		for(UserRoleUrl userRoleUrl:list){
			userRoleUrlService.delete(userRoleUrl);
		}
		return 1;
	}
}