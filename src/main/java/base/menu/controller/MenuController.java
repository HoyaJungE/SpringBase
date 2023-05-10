package base.menu.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import base.common.common.CommandMap;
import base.menu.service.MenuService;

@Controller
@RequestMapping(value = "/menu/*")
public class MenuController {

	Logger log = Logger.getLogger(this.getClass()); //로그
	
	@Resource(name="MenuService")
	private MenuService menuService;
	
	@RequestMapping(value = "menuList.do")
	public String menuList(CommandMap commandMap, HttpServletRequest request) throws Exception {
		request.setAttribute("menuList", menuService.selectMenuList(commandMap.getMap()));
		return "menu/menuList";
	}
		
	@RequestMapping(value = "selectMenuListData.do")
	public ModelAndView selectMenuListData(CommandMap commandMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("jsonView");
    	
    	List<Map<String,Object>> list = menuService.selectMenuList(commandMap.getMap());
    	mv.addObject("menuList", list);
		return mv;
	}

}
