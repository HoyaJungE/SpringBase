package base.member.my.service;

import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;

import base.common.common.CommandMap;

import base.common.common.CommandMap;



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
	List<Map<String, Object>> myPointList(CommandMap commandMap) throws Exception;
	//쿠폰 목록
	List<Map<String, Object>> myCouponList(CommandMap commandMap) throws Exception;
	//좋아요 목록
	List<Map<String, Object>> myLikeList(CommandMap commandMap) throws Exception;
	//좋아요 삭제
	void goodsLikeDelete(CommandMap commandMap, HttpServletRequest request) throws Exception;

	List<Map<String, Object>> myDash(CommandMap commandMap) throws Exception; // 마이페이지사이드바
	
	//나의 상품QNA글
	List<Map<String, Object>> myGoodsQnaList(CommandMap commandMap) throws Exception;
	
	//나의 상품Review글
	List<Map<String, Object>> myReviewList(CommandMap commandMap); 
	
	
	

}
