package base.menu.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import base.common.common.ParamMap;
import base.menu.service.MenuService;

@Controller
@RequestMapping(value = "/menu/*")
public class MenuController {

	Logger log = Logger.getLogger(this.getClass()); //로그
	
	@Resource(name="MenuService")
	private MenuService menuService;
	
	@RequestMapping(value = "menuList.do")
	public String menuList(ParamMap paramMap, HttpServletRequest request) throws Exception {
		return "menu/menuList.tiles";
	}
		
	@RequestMapping(value = "selectMenuListData.do")
	public ModelAndView selectMenuListData(ParamMap paramMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("jsonView");
    	
    	List<Map<String,Object>> list = menuService.selectMenuList(paramMap.getMap());
    	mv.addObject("menuList", list);
		return mv;
	}
	
	@RequestMapping(value = "menuDetail.do")
	public String menuDetail(ParamMap paramMap, HttpServletRequest request) throws Exception {
		request.setAttribute("map", menuService.selectMenuDetail(paramMap.getMap()));
		return "menu/menuDetail.tiles";
	}
	
	@RequestMapping(value="/menuInsertPage.do")
	public String menuInsertPage(ParamMap ParamMap) throws Exception{
		return "menu/menuInsert.tiles";
	}
	
	@RequestMapping(value="/insertMenu.do")
	public String insertFaq(ParamMap ParamMap, HttpServletRequest request) throws Exception{
		menuService.insertMenu(ParamMap.getMap(), request);
		
		return "redirect:/menu/menuList.do";
	}
	
	@RequestMapping(value = "menuUpdate.do")
	public String menuUpdate(ParamMap paramMap, HttpServletRequest request) throws Exception {
		menuService.updateMenu(paramMap.getMap(), request);
		return "redirect:/menu/menuList.do";
	}
	
	@RequestMapping(value="/board/faq/deleteFaq.do")
	public String deleteFaq(ParamMap ParamMap) throws Exception{
		menuService.deleteMenu(ParamMap.getMap());
		
		return "redirect:/menu/menuList.do";
	}

}
