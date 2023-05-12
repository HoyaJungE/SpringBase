package base.shop.order.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import base.common.common.ParamMap;
import base.shop.basket.service.BasketService;
import base.shop.order.service.OrderService;

@Controller
public class OrderController {
	
	Logger log = Logger.getLogger(this.getClass()); //로그
	/*
	 * @Resource(name="orderService") private OrderService orderService;
	 */
	@Resource(name="basketService")
	private BasketService basketService;
	
	@Resource(name="orderService")
	private OrderService orderService;
	
	
	//장바구니 모두구매
	@RequestMapping(value="/order/basketAllOrderWrite.do")
	public ModelAndView basketAllOrderSelect(ParamMap ParamMap, HttpServletRequest request) throws Exception {
		
		ModelAndView mv = new ModelAndView("order/orderWrite");
		Object MEMBER_NO = ""; //세션값 가져오기 
		HttpSession session = request.getSession(); 
		MEMBER_NO = (Object)session.getAttribute("SESSION_NO"); 
		ParamMap.remove("MEMBER_NO"); // 기존 회원번호 데이터 삭제 
		ParamMap.put("MEMBER_NO", MEMBER_NO); // 세션 값으로 적용
		List<Map<String,Object>> list = basketService.basketList(ParamMap);
		Map<String,Object> map = orderService.orderMemberInfo(ParamMap, request);
		List<Map<String,Object>> list2 = orderService.memberCoupon(ParamMap);
		mv.addObject("list", list);
		mv.addObject("list2", list2);
		mv.addObject("map", map);
		System.out.println(list);
		System.out.println(map);
		System.out.println(list2);
		return mv;
	}
	
	//장바구니 선택상품 구매
	@RequestMapping(value="/order/basketSelectOrder.do")
	public ModelAndView basketSelect(ParamMap ParamMap, HttpServletRequest request) throws Exception {
		
		ModelAndView mv = new ModelAndView("order/orderWrite");
		Object MEMBER_NO = ""; //세션값 가져오기 
		HttpSession session = request.getSession(); 
		MEMBER_NO = (Object)session.getAttribute("SESSION_NO"); 
		ParamMap.remove("MEMBER_NO"); // 기존 회원번호 데이터 삭제 
		ParamMap.put("MEMBER_NO", MEMBER_NO); // 세션 값으로 적용
		List<Map<String,Object>> list = basketService.basketSelectList(ParamMap, request); //선택한 장바구니번호의 상품 
		Map<String,Object> map = orderService.orderMemberInfo(ParamMap, request); //주문자정보
		List<Map<String,Object>> list2 = orderService.memberCoupon(ParamMap); //주문자 쿠폰내역
		mv.addObject("list", list);
		mv.addObject("map", map);
		mv.addObject("list2", list2);
		System.out.println(list);
		System.out.println(map);
		System.out.println(list2);
		return mv;
	}
	
	//상품 주문완료(결제)
	@RequestMapping(value="/order/orderPay.do")
	public ModelAndView orderPay(ParamMap ParamMap, HttpServletRequest request) throws Exception {
		ModelAndView mv = new ModelAndView("order/orderFinish");
			
		Object MEMBER_NO = ""; //세션값 가져오기 
		HttpSession session = request.getSession(); 
		MEMBER_NO = (Object)session.getAttribute("SESSION_NO"); 
		ParamMap.remove("MEMBER_NO"); // 기존 회원번호 데이터 삭제 
		ParamMap.put("MEMBER_NO", MEMBER_NO); // 세션 값으로 적용	
		orderService.insertOrder(ParamMap, request);
		orderService.updateMember(ParamMap, request);
		Map<String,Object> map = orderService.selectOrder(ParamMap, request);
		mv.addObject("map", map); 
		return mv;
		}
	
	//주문자 정보변경
		@RequestMapping(value="/order/orderModify.do")
		public ModelAndView orderModify(ParamMap ParamMap, HttpServletRequest request) throws Exception {
			System.out.println(ParamMap.get("ORDER_NO"));
			
			ModelAndView mv = new ModelAndView("redirect:/my_detail.do");
			mv.addObject("order_no", ParamMap.get("ORDER_NO"));
			//수량수정
			orderService.orderModify(ParamMap, request);
			return mv;
		}
	


}
