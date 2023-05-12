package base.common.main.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import base.common.common.ParamMap;
import base.shop.goods.service.GoodsService;

@Controller
public class MainController {

	Logger log = Logger.getLogger(this.getClass()); //로그
	
	@Resource(name="goodsService")
	private GoodsService goodsService;
	
	
	@RequestMapping(value = "main.do")
	public ModelAndView openMainList(ParamMap ParamMap, HttpServletRequest request)  // 메인 
			throws Exception {
		ModelAndView mv = new ModelAndView("main");
		
		mv.addObject("IDX", ParamMap.getMap().get("IDX"));
		
		String filePath_temp = request.getContextPath() + "/file/";
		mv.addObject("path", filePath_temp);
		request.setAttribute("path", filePath_temp);
		return mv;
	}

	@RequestMapping(value = "mainList.do")
	public ModelAndView selectMainList(ParamMap ParamMap, HttpServletRequest request)  // 메인 json
			throws Exception {

		ModelAndView mv = new ModelAndView("jsonView");

		List<Map<String,Object>> newList = goodsService.newGoodsList(ParamMap.getMap());
		newList.get(0);
		if (newList.size() != 0) {
			newList.get(0).get("");
		}

		mv.addObject("NewList", newList);
		if (newList.size() > 0) {
			mv.addObject("TOTAL", newList.get(0).get("TOTAL_COUNT"));
		} else {
			mv.addObject("TOTAL", 0);
		}
		
		List<Map<String,Object>> bestList = goodsService.bestGoodsList(ParamMap.getMap());
		bestList.get(0);
		if (bestList.size() != 0) {
			bestList.get(0).get("");
		}

		mv.addObject("BestList", bestList);
		if (bestList.size() > 0) {
			mv.addObject("TOTAL1", bestList.get(0).get("TOTAL_COUNT"));
		} else {
			mv.addObject("TOTAL1", 0);
		}

		return mv;
	}
	
	
	
	/*
	 * @RequestMapping(value="main.do" ,method = RequestMethod.GET) // url public
	 * ModelAndView mainView(ParamMap ParamMap) throws Exception { // 메인 상품 리스트
	 * 
	 * ModelAndView mv = new ModelAndView("main"); // 보낼 url
	 * 
	 * List<Map<String,Object>> newList =
	 * goodsService.newGoodsList(ParamMap.getMap()); mv.addObject("newList",
	 * newList);
	 * 
	 * 
	 * List<Map<String,Object>> bestList =
	 * goodsService.bestGoodsList(ParamMap.getMap()); mv.addObject("bestList",
	 * bestList);
	 * 
	 * return mv; }
	 */
}
