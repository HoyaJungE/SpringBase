package base.menu.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import base.common.common.CommandMap;
import base.menu.service.MenuService;

@Controller
public class MenuController {

	Logger log = Logger.getLogger(this.getClass()); //로그
	
	@Resource(name="MenuService")
	private MenuService menuService;
	
	@RequestMapping(value = "menuList.do")
	public ModelAndView openMainList(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("main");
		
		mv.addObject("IDX", commandMap.getMap().get("IDX"));
		
		String filePath_temp = request.getContextPath() + "/file/";
		mv.addObject("path", filePath_temp);
		request.setAttribute("path", filePath_temp);
		return mv;
	}
	
	

}
