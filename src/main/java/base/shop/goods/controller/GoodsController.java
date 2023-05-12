package base.shop.goods.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import base.common.common.ParamMap;
import base.shop.basket.dao.BasketDAO;
import base.shop.basket.service.BasketService;
import base.shop.goods.service.GoodsService;
import base.shop.order.dao.OrderDAO;
import base.shop.order.service.OrderService;

@Controller
public class GoodsController {

	Logger log = Logger.getLogger(this.getClass()); // 로그

	@Resource(name = "goodsService")
	private GoodsService goodsService;

	@Resource(name = "orderService")
	private OrderService orderService;

	@Resource(name = "basketService")
	private BasketService basketService;

	@RequestMapping(value = "/shop/newGoodsList.do") 
	public ModelAndView newGoodsList(ParamMap ParamMap) throws Exception { // NewItem 리스트 출력

		ModelAndView mv = new ModelAndView("shop/goodsList");

		List<Map<String, Object>> list = goodsService.newGoodsList(ParamMap.getMap());

		mv.addObject("list", list);
		mv.addObject("titleMain", "새상품");
		
		return mv;
		
	}

	@RequestMapping(value = "/shop/bestGoodsList.do") // url
	public ModelAndView bestGoodsList(ParamMap ParamMap) throws Exception { // BEST 리스트 출력

		ModelAndView mv = new ModelAndView("shop/goodsList");

		List<Map<String, Object>> list = goodsService.bestGoodsList(ParamMap.getMap());
		
		mv.addObject("list", list);
		mv.addObject("titleMain", "베스트");

		return mv;

	}

	@RequestMapping(value = "/shop/goodsList/{cate}/{orderBy}.do")
	public ModelAndView openBoardList(@PathVariable String cate, @PathVariable String orderBy, ParamMap ParamMap,
			@RequestParam(value = "keyword", defaultValue = "") String keyword, HttpServletRequest request)  // 카테고리별 상품리스트
			throws Exception {
		ModelAndView mv = new ModelAndView("/shop/cateGoodsList");
		ParamMap.put("cate", cate);
		request.setAttribute("keyword", keyword);
		System.out.println("카테고리 검색확인=" + ParamMap.getMap());
		System.out.println("검색키워드=" + keyword);
		mv.addObject("IDX", ParamMap.getMap().get("IDX"));
		if ("NewItem".equals(orderBy)) { // 신상품순
			ParamMap.put("orderBy", "GOODS_DATE");
			ParamMap.put("orderSort", "DESC");
		} else if ("favorite".equals(orderBy)) { // 인기상품
			ParamMap.put("orderBy", "GOODS_READCNT");
			ParamMap.put("orderSort", "DESC");
		} else if ("low".equals(orderBy)) { // 낮은가격순
			ParamMap.put("orderBy", "GOODS_SELL_PRICE");
			ParamMap.put("orderSort", "ASC");
		} else if ("high".equals(orderBy)) { // 높은가격순
			ParamMap.put("orderBy", "GOODS_SELL_PRICE");
			ParamMap.put("orderSort", "DESC");
		}
		mv.addObject("category", cate);
		String filePath_temp = request.getContextPath() + "/file/";
		mv.addObject("path", filePath_temp);
		request.setAttribute("path", filePath_temp);
		return mv;
	}

	@RequestMapping(value = "/shop/cateGoodsList/{cate}/{orderBy}.do")
	public ModelAndView selectGoodsList(@PathVariable String cate, @PathVariable String orderBy, ParamMap ParamMap,
			@RequestParam(value = "keyword", defaultValue = "") String keyword, HttpServletRequest request) // 카테고리별 리스트 제이슨
			throws Exception {

		ModelAndView mv = new ModelAndView("jsonView");
		List<Map<String, Object>> list = null;

		System.out.println("검색어: " + keyword);
		System.out.println("카테고리 = " + cate);
		System.out.println("카테고리 순서 =" + orderBy);

		// category
		ParamMap.put("cate", cate);
		ParamMap.put("orderBy", orderBy);

		// order by
		if ("NewItem".equals(orderBy)) { // 신상품순
			ParamMap.put("orderBy", "GOODS_DATE");
			ParamMap.put("orderSort", "DESC");
		} else if ("favorite".equals(orderBy)) { // 인기상품
			ParamMap.put("orderBy", "GOODS_READCNT");
			ParamMap.put("orderSort", "DESC");
		} else if ("low".equals(orderBy)) { // 낮은가격순
			ParamMap.put("orderBy", "GOODS_SELL_PRICE");
			ParamMap.put("orderSort", "ASC");
		} else if ("high".equals(orderBy)) { // 높은가격순
			ParamMap.put("orderBy", "GOODS_SELL_PRICE");
			ParamMap.put("orderSort", "DESC");
		}

		list = goodsService.cateGoodsList(ParamMap.getMap(), keyword);

		//System.out.println("토탈카운트" + list.get(0).get("TOTAL_COUNT"));

		mv.addObject("list", list);
		if (list.size() > 0) {
			mv.addObject("TOTAL", list.get(0).get("TOTAL_COUNT"));
		} else {
			mv.addObject("TOTAL", 0);
		}
		// mv.addObject("category", cate);

		return mv;
	}

	@RequestMapping(value = "/shop/openMainSearch.do")
	public ModelAndView openMainSearch(ParamMap ParamMap,
			@RequestParam(value = "keyword", defaultValue = "") String keyword, HttpServletRequest request) // 메인에서 검색시 리스트
			throws Exception {
		ModelAndView mv = new ModelAndView("/shop/mainSearch");

		request.setAttribute("keyword", keyword);
		System.out.println("카테고리 검색확인=" + ParamMap.getMap());
		System.out.println("검색키워드=" + keyword);
		mv.addObject("keyword");
		mv.addObject("IDX", ParamMap.getMap().get("IDX"));

		String filePath_temp = request.getContextPath() + "/file/";
		mv.addObject("path", filePath_temp);
		request.setAttribute("path", filePath_temp);
		return mv;
	}

	@RequestMapping(value = "/shop/mainSearch.do")
	public ModelAndView mainSearch(ParamMap ParamMap,
			@RequestParam(value = "keyword", defaultValue = "") String keyword, HttpServletRequest request) // 메인에서 검색시 리스트 제이슨
			throws Exception {

		ModelAndView mv = new ModelAndView("jsonView");
		List<Map<String, Object>> list = null;

		System.out.println("메인검색어: " + keyword);

		list = goodsService.mainSearch(ParamMap.getMap(), keyword);

		System.out.println("토탈카운트" + list.get(0).get("TOTAL_COUNT"));

		list.get(0);
		if (list.size() != 0) {
			list.get(0).get("");
		}
 
		mv.addObject("list", list);
		if (list.size() > 0) {
			mv.addObject("TOTAL", list.get(0).get("TOTAL_COUNT"));
		} else {
			mv.addObject("TOTAL", 0);
		}

		return mv;
	}


	@RequestMapping(value = "/shop/goodsDetail.do") 
	public ModelAndView goodsDetail(ParamMap ParamMap, HttpServletRequest request) throws Exception { // 상품디테일
																											
		ModelAndView mv = new ModelAndView("shop/goodsDetail");

		Map<String, Object> map = goodsService.selectGoodsDetail(ParamMap.getMap(), request);
		System.out.println("IDX = " + ParamMap.getMap());
		Map<String, Object> IDX = ParamMap.getMap();
		System.out.println("map = " + map);
		
		mv.addObject("map", map.get("map")); // 상품의 PK값
		mv.addObject("list", map); // 상품 상세 정보입니다

		Map<String, Object> map1 = goodsService.selectGoodsAtt(ParamMap.getMap());

		System.out.println("map1=" + map1);

		String Size = map1.get("GOODS_ATT_SIZE").toString();
		String[] SizeList = Size.split(",");
		String Color = map1.get("GOODS_ATT_COLOR").toString();
		String[] ColorList = Color.split(",");
		System.out.println("SizeList" + SizeList.toString());

		ArrayList<String> arrColor = new ArrayList<>();
		ArrayList<String> arrSize = new ArrayList<>();
		TreeSet<String> treeSet = new TreeSet<>();
		for (String data : SizeList) {
			if (!arrSize.contains(data))
				arrSize.add(data);
		}

		for (String data : ColorList) {
			if (!arrColor.contains(data))
				arrColor.add(data);
		}

		System.out.println("arrColor=" + arrColor);
		System.out.println("arrSize=" + arrSize);
		int ColorSize = arrColor.size();
		int Sizecnt = arrSize.size();

		mv.addObject("Color", arrColor); // 컬러 총 갯수
		mv.addObject("ColorSize", ColorSize); // 컬러 종류
		mv.addObject("Size", arrSize); // 사이즈 종류
		mv.addObject("Sizecnt", Sizecnt);
		return mv;

	}

	@RequestMapping(value = "/shop/goodsDetailList.do")
	public ModelAndView goodsDetailList(ParamMap ParamMap, HttpServletRequest request) throws Exception { // 상품 디테일 제이슨

		ModelAndView mv = new ModelAndView("jsonView");
		List<Map<String, Object>> list = null;

		list = goodsService.selectGoodsQna(ParamMap.getMap()); // QNA 리스트

		System.out.println("디테일리스트=" + list);
		

		mv.addObject("list", list);
		
		if (list.size() > 0) {
			mv.addObject("TOTAL", list.get(0).get("TOTAL_COUNT"));
		} else {
			mv.addObject("TOTAL", 0);
		}

		List<Map<String, Object>> reviewList = goodsService.selectReviewList(ParamMap.getMap()); // Review 리스트
		System.out.println("리뷰리스트=" + reviewList);
		
		mv.addObject("reviewList", reviewList);
		if (reviewList.size() > 0) {
			mv.addObject("TOTAL1", reviewList.get(0).get("TOTAL_COUNT1"));
		} else {
			mv.addObject("TOTAL1", 0);
		}

		return mv;
	}

	@RequestMapping(value = "/shop/openGoodsWrite.do") // url
	public ModelAndView goodsWriteForm(ParamMap ParamMap) throws Exception { // 상품등록 폼

		ModelAndView mv = new ModelAndView("shop/goodsWrite");
		mv.addObject("type", "write");
		mv.addObject("title", "상품등록");
		return mv;

	}

	@RequestMapping(value = "/shop/goodsWrite.do", method = RequestMethod.POST) 
	public ModelAndView goodsWrite(ParamMap ParamMap, HttpServletRequest request) throws Exception { // 상품등록 

		ModelAndView mv = new ModelAndView("redirect:http:/stu/main.do"); 

		goodsService.insertGoods(ParamMap.getMap(), request);
		System.out.println("글쓰기입니당" + ParamMap.getMap());
		return mv;

	}
	
	
	@RequestMapping(value = "/shop/goodsDelete.do", method = RequestMethod.POST) 
	public ModelAndView deleteGoods(ParamMap ParamMap, HttpServletRequest request) throws Exception { // 상품삭제(숨김) 

		ModelAndView mv = new ModelAndView("redirect:http:/stu/main.do"); 

		goodsService.deleteGoods(ParamMap.getMap(), request);
		System.out.println("상품삭제(숨김) = " + ParamMap.getMap());
		return mv;

	}
	

	@RequestMapping(value = "/shop/goodsLike.do", method = RequestMethod.POST)
	public ModelAndView goodsLike(ParamMap ParamMap, HttpServletRequest request) throws Exception { // 상품디테일에서 좋아요 추가
		ModelAndView mv = new ModelAndView("redirect:/shop/goodsDetail.do");

		mv.addObject("IDX", ParamMap.getMap().get("GOODS_NO"));
		System.out.println("좋아요!!!!=" + ParamMap.getMap().get("GOODS_NO"));

		Object MEMBER_NO = "";
		// 세션값 가져오기
		HttpSession session = request.getSession();
		MEMBER_NO = (Object) session.getAttribute("SESSION_NO"); // 세션 아이디
		// 기존 회원번호 데이터 삭제
		ParamMap.remove("MEMBER_NO");
		// 세션 값으로 적용
		ParamMap.put("MEMBER_NO", MEMBER_NO);

		Map<String, Object> map = basketService.selectGoodsLike(ParamMap, request);
		String like_cnt = String.valueOf(map.get("LIKE_CNT"));

		if (like_cnt.equals("0")) {
			basketService.insertGoodsLike(ParamMap, request);
		}
		return mv;
	}

	@RequestMapping(value = "/shop/basketPopUp.do", method = RequestMethod.GET)
	public ModelAndView basketPopUp(ParamMap ParamMap) throws Exception { // 상품디테일 장바구니 클릭시 팝업창
		ModelAndView mv = new ModelAndView("/shop/basketPopUp");

		return mv;
	}



	@RequestMapping(value = "/shop/insertBasket.do", method = RequestMethod.POST)
	public ModelAndView insertBasket(ParamMap ParamMap, HttpServletRequest request) throws Exception { // 상품디테일에서 장바구니 추가 
		ModelAndView mv = new ModelAndView("redirect:/shop/goodsDetail.do");
		
		ParamMap.remove("resultList");
		Object MEMBER_NO = "";
		// 세션값 가져오기
		HttpSession session = request.getSession();
		MEMBER_NO = (Object) session.getAttribute("SESSION_NO");
		// 기존 회원번호 데이터 삭제
		ParamMap.remove("MEMBER_NO");
		// 세션 값으로 적용
		ParamMap.put("MEMBER_NO", MEMBER_NO);
		
		//장바구니에 넣을 상품이 한개일때
		if (ParamMap.get("ORDER_SIZE").getClass().getName().equals("java.lang.String")) { 
			Map<String, Object> map = new HashMap<String, Object>();
			System.out.println("ParamMap1=" + ParamMap.getMap());
			map.put("IDX", ParamMap.get("IDX"));
			map.put("MEMBER_NO", ParamMap.get("MEMBER_NO"));
			map.put("ORDER_SIZE", ParamMap.get("ORDER_SIZE"));
			map.put("ORDER_COLOR", ParamMap.get("ORDER_COLOR"));
			map.put("BASKET_GOODS_AMOUNT", ParamMap.get("BASKET_GOODS_AMOUNT"));
			map.put("GUBUN", "0");
			goodsService.insertBasket(map, request);
		} else { //장바구니에 넣을 상품이 두가지 이상일때(색상,사이즈가 다른) 
			System.out.println("ParamMap2=" + ParamMap.getMap());
			String[] Size = (String[]) ParamMap.getMap().get("ORDER_SIZE");
			String[] Color = (String[]) ParamMap.getMap().get("ORDER_COLOR");
			String[] Amount = (String[]) ParamMap.getMap().get("BASKET_GOODS_AMOUNT");
			String[] Goods_No = (String[]) ParamMap.getMap().get("IDX");
			System.out.println("다중 사이즈0=" + Goods_No[0]);
			System.out.println("다중 사이즈1=" + Goods_No[1]);
			Map<String, Object> map1 = new HashMap<String, Object>();
			
			for (int j = 0; j <= Size.length - 1; j++) {
				map1.put("ORDER_SIZE", Size[j]);
				map1.put("ORDER_COLOR", Color[j]);
				map1.put("BASKET_GOODS_AMOUNT", Amount[j]);
				map1.put("IDX", Goods_No[j]);
				map1.put("MEMBER_NO", ParamMap.get("MEMBER_NO"));
				map1.put("GUBUN", "0");
				System.out.println("Size1111=" + Size[j]);
				goodsService.insertBasket(map1, request);
			}
		}
		mv.addObject("IDX", ParamMap.getMap().get("IDX"));
		return mv;
	}

	@RequestMapping(value = "/shop/goodsOrder.do", method = RequestMethod.POST)
	public ModelAndView goodsOrder(ParamMap ParamMap, HttpServletRequest request) throws Exception { // 상품디테일에서 구매 
		ModelAndView mv = new ModelAndView("order/orderWrite");

		goodsService.gumeListDelete(ParamMap.getMap());
		Object MEMBER_NO = "";
		// 세션값 가져오기
		HttpSession session = request.getSession();
		MEMBER_NO = (Object) session.getAttribute("SESSION_NO");
		// 기존 회원번호 데이터 삭제
		ParamMap.remove("MEMBER_NO");
		// 세션 값으로 적용
		ParamMap.put("MEMBER_NO", MEMBER_NO);
		
		

		System.out.println("ParamMap=" + ParamMap.getMap());
		ParamMap.remove("resultList");

		if (ParamMap.get("ORDER_SIZE").getClass().getName().equals("java.lang.String")) { // 일반 스트링으로 왔을 때
			Map<String, Object> map = new HashMap<String, Object>();                        // 구매시 장바구니에 등록
			System.out.println("ParamMap1=" + ParamMap.getMap());

			map.put("IDX", ParamMap.get("IDX"));
			map.put("MEMBER_NO", ParamMap.get("MEMBER_NO"));
			map.put("ORDER_SIZE", ParamMap.get("ORDER_SIZE"));
			map.put("ORDER_COLOR", ParamMap.get("ORDER_COLOR"));
			map.put("BASKET_GOODS_AMOUNT", ParamMap.get("BASKET_GOODS_AMOUNT"));
			map.put("GUBUN", "1");
			goodsService.insertBasket(map, request);
		} else { // 배열로 왔을 때
			System.out.println("ParamMap2=" + ParamMap.getMap());
			String[] Size = (String[]) ParamMap.getMap().get("ORDER_SIZE");
			String[] Color = (String[]) ParamMap.getMap().get("ORDER_COLOR");
			String[] Amount = (String[]) ParamMap.getMap().get("BASKET_GOODS_AMOUNT");
			String[] Goods_No = (String[]) ParamMap.getMap().get("IDX");

			System.out.println("다중 사이즈0=" + Goods_No[0]);
			System.out.println("다중 사이즈1=" + Goods_No[1]);
			Map<String, Object> map1 = new HashMap<String, Object>();
			for (int j = 0; j <= Size.length - 1; j++) {
				map1.put("ORDER_SIZE", Size[j]);
				map1.put("ORDER_COLOR", Color[j]);
				map1.put("BASKET_GOODS_AMOUNT", Amount[j]);
				map1.put("IDX", Goods_No[j]);
				map1.put("MEMBER_NO", ParamMap.get("MEMBER_NO"));
				map1.put("GUBUN", "1");
				System.out.println("Size1111=" + Size[j]);
				goodsService.insertBasket(map1, request);
			}
		}

		List<Map<String, Object>> list0 = goodsService.selectBasketNo(ParamMap.getMap()); // 장바구니 PK값 가져오기
		System.out.println("장바구니넘버111111" + list0.get(0).get("BASKET_NO"));

		ParamMap.remove("SELECT_BASKET_NO");
		ParamMap.put("SELECT_BASKET_NO", list0.get(0).get("BASKET_NO"));

		List<Map<String, Object>> list = basketService.basketSelectList(ParamMap, request); // 장바구니에 있는 정보들 

		Map<String, Object> map = orderService.orderMemberInfo(ParamMap, request); // 회원의 정보

		List<Map<String, Object>> list2 = orderService.memberCoupon(ParamMap); // 회원의 쿠폰
		mv.addObject("list", list);
		mv.addObject("map", map);
		mv.addObject("list2", list2);
		System.out.println(list);
		System.out.println(map);
		System.out.println(list2);
		return mv;
	}

	@RequestMapping(value = "/shop/goodsModifyForm.do")
	public ModelAndView goodsModifyForm(ParamMap ParamMap, HttpServletRequest request) throws Exception { // 상품 수정폼(관리자)
		ModelAndView mv = new ModelAndView("shop/goodsWrite");

		Map<String, Object> map = goodsService.selectGoodsDetail(ParamMap.getMap(), request);
		System.out.println("수정폼1=" + ParamMap.getMap());
		System.out.println("수정폼2=" + map);
		mv.addObject("map", map);
		mv.addObject("list", map.get("list"));
		mv.addObject("type", "modify");
		mv.addObject("title", "수정하기");
		System.out.println("수정폼3=" + map);
		return mv;
	}

	@RequestMapping(value = "/shop/goodsModify.do")
	public ModelAndView goodsModify(ParamMap ParamMap, HttpServletRequest request) throws Exception { // 상품 수정완료(관리자)
		ModelAndView mv = new ModelAndView("redirect:/main.do");

		goodsService.updateGoods(ParamMap.getMap(), request);
		System.out.println("업데이트Map=" + ParamMap);
		System.out.println("업데이트Map=" + ParamMap.getMap());
		mv.addObject("IDX", ParamMap.getMap().get("IDX"));

		return mv;
	}

	@RequestMapping(value = "/shop/openQnaForm.do")
	public ModelAndView openGoodsQna(ParamMap ParamMap) throws Exception { // QNA 등록 폼

		ModelAndView mv = new ModelAndView("shop/goodsQnaForm"); 
		mv.addObject("IDX", ParamMap.get("IDX"));
		return mv;

	}

	@RequestMapping(value = "/shop/goodsQnaInsert.do", method = RequestMethod.POST) // QNA 등록
	public ModelAndView insertGoodsQna(ParamMap ParamMap, HttpServletRequest request) throws Exception { // QNA 등록완료

		ModelAndView mv = new ModelAndView("redirect:/shop/goodsDetail.do"); 

		goodsService.insertGoodsQna(ParamMap.getMap(), request);
		System.out.println("상품문의작성=" + ParamMap.getMap());
		mv.addObject("IDX", ParamMap.get("IDX"));

		return mv;

	}
	
	@RequestMapping(value = "/shop/updateGoodsQna.do", method = RequestMethod.POST)
	public ModelAndView updateGoodsQna(ParamMap ParamMap, HttpServletRequest request) throws Exception { // QNA 답변 수정 및 등록

		ModelAndView mv = new ModelAndView("redirect:/shop/goodsDetail.do"); // 보낼 url
		System.out.println("상품 QNA 답변등록=" + ParamMap.getMap());
		goodsService.updateGoodsQna(ParamMap.getMap(), request);

		mv.addObject("GOODS_QNA_NO", ParamMap.get("GOODS_QNA_NO"));
		
		return mv;

	}

	@RequestMapping(value = "/shop/openReviewForm.do")
	public ModelAndView reviewForm(ParamMap ParamMap) throws Exception { // Review 등록 폼

		ModelAndView mv = new ModelAndView("shop/reviewForm");
		System.out.println("111111111리뷰폼11111111=" + ParamMap.getMap());
		mv.addObject("IDX", ParamMap.get("IDX"));
		
		mv.addObject("title", "REVIEW 등록");
		
		return mv;

	}

	@RequestMapping(value = "/shop/insertReview.do", method = RequestMethod.POST)
	public ModelAndView insertGoodsReview(ParamMap ParamMap, HttpServletRequest request) throws Exception { // Review 등록완료

		ModelAndView mv = new ModelAndView("redirect:/shop/goodsDetail.do"); // 보낼 url
		System.out.println("리뷰등록=" + ParamMap.getMap());
		goodsService.insertGoodsReview(ParamMap.getMap(), request);

		mv.addObject("IDX", ParamMap.get("IDX"));
		
		return mv;

	}
	
	
	@RequestMapping(value = "/shop/openReviewUpdateForm.do")
	public ModelAndView reviewUpdateForm(ParamMap ParamMap) throws Exception { // Review 수정

		ModelAndView mv = new ModelAndView("shop/reviewForm");
		System.out.println("111111111리뷰폼11111111=" + ParamMap.getMap());
		mv.addObject("IDX", ParamMap.get("IDX"));
		
		mv.addObject("title", "REVIEW 수정");
		mv.addObject("type", "modify");
		return mv;

	}
	
	@RequestMapping(value = "/shop/updateReview.do", method = RequestMethod.POST)
	public ModelAndView updateReview(ParamMap ParamMap, HttpServletRequest request) throws Exception { // Review 수정완료

		ModelAndView mv = new ModelAndView("redirect:/shop/goodsDetail.do"); // 보낼 url
		System.out.println("리뷰등록=" + ParamMap.getMap());
		goodsService.updateReview(ParamMap.getMap(), request);

		mv.addObject("IDX", ParamMap.get("IDX"));
		
		return mv;

	}

}
