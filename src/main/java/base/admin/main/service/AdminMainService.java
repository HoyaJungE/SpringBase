package base.admin.main.service;

import java.util.List;
import java.util.Map;

import base.common.common.ParamMap;

public interface AdminMainService {
	
	List<Map<String, Object>> dashBoard(ParamMap ParamMap) throws Exception; // AdminMain대쉬보드
	
	List<Map<String, Object>> order_admin_a(ParamMap map) throws Exception; // 주문/배송-신규주문건
	
	void order_state(ParamMap map) throws Exception; // 주문/배송- 주문상태변경
	
	void order_state_ex(ParamMap ParamMap) throws Exception; // 주문/배송 - 송장번호 입력

	List<Map<String, Object>> order_detail(ParamMap ParamMap) throws Exception; //어드민 - 주문변경 상세

	List<Map<String, Object>> order_detail_sub(ParamMap ParamMap) throws Exception;//어드민 - 주문변경 detail상세

	List<Map<String, Object>> as_admin_list(ParamMap ParamMap) throws Exception; // 교환.환불.AS 신청목록 

	void as_cancle_a(ParamMap ParamMap) throws Exception; // 교환.환불.AS (1)요청확인전에서 취소

	void as_cancle_b(ParamMap ParamMap) throws Exception; // 교환.환불.AS (2)요청확인후에서 취소

	void as_ok_a(ParamMap ParamMap) throws Exception; // as_list 교환.환불.AS (1)신규요청 처리

	List<Map<String, Object>> change_form_a(ParamMap ParamMap) throws Exception; //AS_list에서 정보 가져옴  전부* 

	List<Map<String, Object>> change_form_b(ParamMap ParamMap) throws Exception; //교환요청한 상품속성 가져옴

	void change_detail_insert(ParamMap ParamMap) throws Exception; // order_detail에 insert 시킴 10(출고)

	void change_detail_state(ParamMap ParamMap) throws Exception; //order_detail에서 detail_state 20(반품)

	void change_goods_att_plus(ParamMap ParamMap) throws Exception; //goods_attribute에서 (반품)상품속성번호에 수량 증가

	void change_goods_att_minus(ParamMap ParamMap) throws Exception; //goods_attribute에서 (출고)상품속성번호에 수량 감소

	void as_ok_b(ParamMap ParamMap) throws Exception; // AS_LIST에서 state = 3, edate=update

	void order_list_chagam(ParamMap ParamMap) throws Exception; ////order_list에서 총결제금액차감 , 총적립포인트차감

	void point_chagam(ParamMap ParamMap) throws Exception; //point에서 적립포인트 차감

	List<Map<String, Object>> point_total(ParamMap ParamMap) throws Exception ; // order_no로 사용자의 최근 point_total을 가져옴

	void as_ok_c(ParamMap ParamMap) throws Exception;
	
	List<Map<String, Object>> selectMemberList(Map<String, Object> map) throws Exception ;

}
