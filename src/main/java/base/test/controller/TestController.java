package base.test.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import base.common.common.ParamMap;
import base.shop.basket.service.BasketService;


@Controller
public class TestController {
	
Logger log = Logger.getLogger(this.getClass()); //로그
	
	//스크립트테스트페이지 이동
	@RequestMapping(value="/testTetris.do")
	public String testTetris(ParamMap ParamMap, HttpServletRequest request) throws Exception {
		
		return "test/tetris";
	}

	//스크립트테스트페이지 이동
	@RequestMapping(value="/testJqgrid.do")
	public String testPage(ParamMap ParamMap, HttpServletRequest request) throws Exception {
		
		return "test/jqgridTest";
	}

}
