package base.menu.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import base.common.common.CommandMap;
import base.shop.goods.service.GoodsService;

@Controller
public class MenuController {

	Logger log = Logger.getLogger(this.getClass()); //로그
	
	@Resource(name="goodsService")
	private GoodsService goodsService;
	
	
	@RequestMapping(value = "main.do")
	public ModelAndView openMainList(CommandMap commandMap, HttpServletRequest request)  // 메인 
			throws Exception {
		ModelAndView mv = new ModelAndView("main");
		
		mv.addObject("IDX", commandMap.getMap().get("IDX"));
		
		String filePath_temp = request.getContextPath() + "/file/";
		mv.addObject("path", filePath_temp);
		request.setAttribute("path", filePath_temp);
		return mv;
	}

}
