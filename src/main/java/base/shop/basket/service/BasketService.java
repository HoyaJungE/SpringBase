package base.shop.basket.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import base.common.common.ParamMap;


public interface BasketService {
	
	//장바구니 전체리스트 검색
	List<Map<String, Object>> basketList(ParamMap ParamMap) throws Exception;

	//장바구니 수량수정
	void basketModify(ParamMap ParamMap, HttpServletRequest request) throws Exception;

	//장바구니 테이블에서 삭제
	void basketDelete(ParamMap ParamMap, HttpServletRequest request) throws Exception;

	//장바구니 전체삭제
	void basketAllDelete(ParamMap ParamMap, HttpServletRequest request) throws Exception;

	//찜하기(좋아요) 존재여부 확인
	Map<String, Object> selectGoodsLike(ParamMap ParamMap, HttpServletRequest request) throws Exception;

	//찜하기(좋아요) 추가
	void insertGoodsLike(ParamMap ParamMap, HttpServletRequest request) throws Exception;

	//장바구니에서 선택상품 주문시 검색 
	List<Map<String, Object>> basketSelectList(ParamMap ParamMap, HttpServletRequest request) throws Exception;

}
