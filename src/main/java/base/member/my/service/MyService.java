package base.member.my.service;

import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;

import base.common.common.ParamMap;

import base.common.common.ParamMap;



public interface MyService {
	
	// 구매 목록
	List<Map<String, Object>> orderList(Map<String, Object> map) throws Exception;
	// 상품명, 상품속성번호
	List<Map<String, Object>> orderList2(Map<String, Object> map) throws Exception;
	// 회원 정보 수정
	public String pwdCheck(Map<String, Object> map, String id) throws Exception;
	
	Map<String,Object> memberModify(String id) throws Exception;
	
	void memberModifyAction(Map<String, Object> map) throws Exception;
	
	void memberDelete(Map<String, Object> map) throws Exception;
	

	//포인트 목록
	List<Map<String, Object>> myPointList(ParamMap ParamMap) throws Exception;
	//쿠폰 목록
	List<Map<String, Object>> myCouponList(ParamMap ParamMap) throws Exception;
	//좋아요 목록
	List<Map<String, Object>> myLikeList(ParamMap ParamMap) throws Exception;
	//좋아요 삭제
	void goodsLikeDelete(ParamMap ParamMap, HttpServletRequest request) throws Exception;

	List<Map<String, Object>> myDash(ParamMap ParamMap) throws Exception; // 마이페이지사이드바
	
	//나의 상품QNA글
	List<Map<String, Object>> myGoodsQnaList(ParamMap ParamMap) throws Exception;
	
	//나의 상품Review글
	List<Map<String, Object>> myReviewList(ParamMap ParamMap); 
	
	
	

}
