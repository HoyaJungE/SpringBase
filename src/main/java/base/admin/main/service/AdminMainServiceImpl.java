package base.admin.main.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import base.admin.main.dao.AdminDAO;
import base.common.common.ParamMap;

@Service("adminMainService")
public class AdminMainServiceImpl implements AdminMainService {
	
	Logger log = Logger.getLogger(this.getClass()); // 로그
	
	@Resource(name="adminDao")
	private AdminDAO adminDao;

	@Override
	public List<Map<String, Object>> dashBoard(ParamMap map) throws Exception { //adminMain대쉬보드
		// TODO Auto-generated method stub
		return adminDao.dashBoard(map);
	}
	
	@Override
	public List<Map<String, Object>> order_admin_a(ParamMap map) throws Exception { //주문/배송-신규주문
		// TODO Auto-generated method stub
		return adminDao.order_admin_a(map);
	}

	@Override
	public void order_state(ParamMap map) throws Exception { // 주문상태 변경
		// TODO Auto-generated method stub
		adminDao.order_state(map);
	}

	@Override
	public void order_state_ex(ParamMap map) throws Exception {  // 주문상태에 배송도 변경
		// TODO Auto-generated method stub
		adminDao.order_state_ex(map);
	}

	@Override
	public List<Map<String, Object>> order_detail(ParamMap ParamMap) throws Exception {
		// TODO Auto-generated method stub
		return adminDao.order_detail(ParamMap);
	}
	
	@Override
	public List<Map<String, Object>> order_detail_sub(ParamMap ParamMap) throws Exception {
		// TODO Auto-generated method stub
		return adminDao.order_detail_sub(ParamMap);
	}

	@Override
	public List<Map<String, Object>> as_admin_list(ParamMap ParamMap) throws Exception {
		// TODO Auto-generated method stub
		return adminDao.as_admin_list(ParamMap);
	}

	@Override
	public void as_cancle_a(ParamMap ParamMap) throws Exception {
		// TODO Auto-generated method stub
		adminDao.as_cancle_a(ParamMap);
	}

	@Override
	public void as_cancle_b(ParamMap ParamMap) throws Exception {
		// TODO Auto-generated method stub
		adminDao.order_list_cancle(ParamMap);
		adminDao.as_cancle_a(ParamMap);
	}

	@Override
	public void as_ok_a(ParamMap ParamMap) throws Exception {
		// TODO Auto-generated method stub
		adminDao.as_ok_state(ParamMap);
		adminDao.as_ok_orderState(ParamMap);
	}

	@Override
	public List<Map<String, Object>> change_form_a(ParamMap ParamMap) throws Exception { //AS_list에서 정보 가져옴  전부* 
		// TODO Auto-generated method stub
		return adminDao.change_form_a(ParamMap);
	}

	@Override
	public List<Map<String, Object>> change_form_b(ParamMap ParamMap) throws Exception { //교환요청한 상품속성 가져옴
		// TODO Auto-generated method stub
		return adminDao.change_form_b(ParamMap);
	}

	@Override
	public void change_detail_insert(ParamMap ParamMap) throws Exception { // order_detail에 insert 시킴 10(출고)
		// TODO Auto-generated method stub
		adminDao.change_detail_insert(ParamMap);
	}

	@Override
	public void change_detail_state(ParamMap ParamMap) throws Exception { //order_detail에서 detail_state 20(반품)
		// TODO Auto-generated method stub
		adminDao.change_detail_state(ParamMap);
	}

	@Override
	public void change_goods_att_plus(ParamMap ParamMap) throws Exception { //goods_attribute에서 (반품)상품속성번호에 수량 증가
		// TODO Auto-generated method stub
		adminDao.change_goods_att_plus(ParamMap);
	}

	@Override
	public void change_goods_att_minus(ParamMap ParamMap) throws Exception { //goods_attribute에서 (출고)상품속성번호에 수량 감소
		// TODO Auto-generated method stub
		adminDao.change_goods_att_minus(ParamMap);
	}

	@Override
	public void as_ok_b(ParamMap ParamMap) throws Exception { // AS_LIST에서 state = 3, edate=update
		// TODO Auto-generated method stub
		adminDao.as_final_state(ParamMap);
		adminDao.change_final_orderState(ParamMap);
	}

	@Override
	public void order_list_chagam(ParamMap ParamMap) throws Exception {
		// TODO Auto-generated method stub
		adminDao.order_list_chagam(ParamMap);
	}

	@Override
	public void point_chagam(ParamMap ParamMap) throws Exception {
		// TODO Auto-generated method stub
		adminDao.point_chagam(ParamMap);
	}

	@Override
	public List<Map<String, Object>> point_total(ParamMap ParamMap) throws Exception {
		// TODO Auto-generated method stub
		return adminDao.point_total(ParamMap);
	}

	@Override
	public void as_ok_c(ParamMap ParamMap) throws Exception {
		// TODO Auto-generated method stub
		adminDao.as_final_state(ParamMap);
		adminDao.cashback_final_orderState(ParamMap);
	}

	public List<Map<String, Object>> selectMemberList(Map<String, Object> map) throws Exception {
		return adminDao.selectMemberList(map);
	}
	
}
