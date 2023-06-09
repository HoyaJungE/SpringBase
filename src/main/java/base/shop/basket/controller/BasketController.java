package base.shop.basket.controller;

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
public class BasketController {
	
Logger log = Logger.getLogger(this.getClass()); //로그
	
	@Resource(name="basketService")
	private BasketService basketService;
	
	//세션값으로 장바구니 전체리스트
	@RequestMapping(value="/basket/basketList.do")
	public ModelAndView basketList(ParamMap ParamMap, HttpServletRequest request) throws Exception {
		
		ModelAndView mv = new ModelAndView("basket/basketList");
		
		Object MEMBER_NO = ""; 
		//세션값 가져오기 
		HttpSession session = request.getSession(); 
		MEMBER_NO = (Object)session.getAttribute("SESSION_NO"); 
		ParamMap.remove("MEMBER_NO"); 
		// 기존 회원번호 데이터 삭제 
		ParamMap.put("MEMBER_NO", MEMBER_NO); 
		// 세션 값으로 적용
		 
		List<Map<String,Object>> list = basketService.basketList(ParamMap);
		//GOODS_NO, BASKET_NO, MEMBER_NO, BASKET_GOODS_AMOUNT, GOODS_ATT_NO, GOODS_ATT_SIZE,
		//GOODS_ATT_COLOR, GOODS_NAME, GOODS_SELL_PRICE, GOODS_SALE_PRICE, UPLOAD_SAVE_NAME, MEMBER_GRADE
		
		mv.addObject("list", list);
		System.out.println(list);
		return mv;
		
	}
	
	//장바구니 수량 수정
	@RequestMapping(value="/basket/basketModify.do")
	public ModelAndView basketModify(ParamMap ParamMap, HttpServletRequest request) throws Exception {
		
		ModelAndView mv = new ModelAndView("redirect:/basket/basketList.do");
		
		//수량수정
		basketService.basketModify(ParamMap, request);
		return mv;
	}
	
	//장바구니 선택삭제(1개)
	@RequestMapping(value="/basket/basketDelete.do")
	public ModelAndView basketDelete(ParamMap ParamMap, HttpServletRequest request) throws Exception {
		
		ModelAndView mv = new ModelAndView("redirect:/basket/basketList.do");
		System.out.println(ParamMap.get("BASKET_NO"));
		basketService.basketDelete(ParamMap, request);
		return mv;
	}
	
	//정바구니 전체삭제
	@RequestMapping(value="/basket/basketAllDelete.do")
	public ModelAndView basketAllDelete(ParamMap ParamMap, HttpServletRequest request) throws Exception {
		
		Object MEMBER_NO = ""; 
		//세션값 가져오기 
		HttpSession session = request.getSession(); 
		MEMBER_NO = (Object)session.getAttribute("SESSION_NO");
		// 기존 회원번호 데이터 삭제 
		ParamMap.remove("MEMBER_NO"); 
		// 세션 값으로 적용
		ParamMap.put("MEMBER_NO", MEMBER_NO); 
		
		ModelAndView mv = new ModelAndView("redirect:/basket/basketList.do");
		System.out.println(ParamMap.get("MEMBER_NO"));
		basketService.basketAllDelete(ParamMap, request);
		return mv;
	}
	
	//장바구니에서 제품 찜하기
	@RequestMapping(value="/basket/like.do")
	public ModelAndView goodsLike(ParamMap ParamMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("redirect:/basket/basketList.do");
		
		Object MEMBER_NO = ""; 
		//세션값 가져오기 
		HttpSession session = request.getSession(); 
		MEMBER_NO = (Object)session.getAttribute("SESSION_NO");
		// 기존 회원번호 데이터 삭제 
		ParamMap.remove("MEMBER_NO"); 
		// 세션 값으로 적용
		ParamMap.put("MEMBER_NO", MEMBER_NO); 
		
		//해당제품 찜하기 여부 확인
		Map<String,Object> map = basketService.selectGoodsLike(ParamMap, request);
		//이미 있으면'1', 없으면'0'
		String like_cnt = String.valueOf(map.get("LIKE_CNT"));
		 
		//없을때 찜하기 추가
		if(like_cnt.equals("0")) { 
			basketService.insertGoodsLike(ParamMap, request);
		}
		return mv;
	}
	
	
	

}
