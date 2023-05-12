package base.admin.main.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import base.common.common.ParamMap;
import base.common.dao.AbstractDao;

@Repository("adminDao")
public class AdminDAO extends AbstractDao{ 
	// 로그값을 받기 위해 AbstractDao를 상속한다 AbstractDao에서 MyBatis와 로그, 커넥션을 가져와 처리한다
	// 굳이 따로 빼서 처리가 필요없다면 adminDao에서 커넥션을 받아와 바로 처리한다
	
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> dashBoard(ParamMap map) throws Exception { //adminMain대쉬보드 
		
		return sqlSession.selectList("admin.dash_count",map);
	}
	
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> order_admin_a(ParamMap map) throws Exception { //admin주문현황 
		
		return sqlSession.selectList("admin.order_admin_a",map.getMap());
	}

	@SuppressWarnings("unchecked")
	public void order_state(ParamMap map) throws Exception {  // 주문상태 변경
		sqlSession.update("admin.order_state",map.getMap());
	}
	
	@SuppressWarnings("unchecked")
	public void order_state_ex(ParamMap ParamMap) throws Exception {
		sqlSession.update("admin.order_state_ex",ParamMap.getMap());
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> order_detail(ParamMap ParamMap) throws Exception {
		return sqlSession.selectList("admin.order_detail", ParamMap.getMap());
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> order_detail_sub(ParamMap ParamMap) throws Exception {
		return sqlSession.selectList("admin.order_detail_sub", ParamMap.getMap());
	}

	public List<Map<String, Object>> as_admin_list(ParamMap ParamMap) throws Exception {
		return sqlSession.selectList("admin.as_admin_list", ParamMap.getMap());
	}

	public void as_cancle_a(ParamMap ParamMap) throws Exception {
		sqlSession.update("admin.as_cancle_a",ParamMap.getMap());
	}

	public void order_list_cancle(ParamMap ParamMap) throws Exception {
		sqlSession.update("admin.order_list_cancle",ParamMap.getMap());
	}

	public void as_ok_state(ParamMap ParamMap) throws Exception {
		sqlSession.update("admin.as_ok_state",ParamMap.getMap());
	}

	public void as_ok_orderState(ParamMap ParamMap) throws Exception {
		sqlSession.update("admin.as_ok_orderState",ParamMap.getMap());
	}

	public List<Map<String, Object>> change_form_a(ParamMap ParamMap) throws Exception {
		return sqlSession.selectList("admin.change_form_a", ParamMap.getMap());
	}

	public List<Map<String, Object>> change_form_b(ParamMap ParamMap) throws Exception {
		return sqlSession.selectList("admin.change_form_b", ParamMap.getMap());
	}

	public void change_detail_insert(ParamMap ParamMap) throws Exception {
		sqlSession.update("admin.change_detail_insert",ParamMap.getMap());
	}

	public void change_detail_state(ParamMap ParamMap) throws Exception {
		sqlSession.update("admin.change_detail_state",ParamMap.getMap());
	}

	public void change_goods_att_plus(ParamMap ParamMap) throws Exception {
		sqlSession.update("admin.change_goods_att_plus",ParamMap.getMap());
	}

	public void change_goods_att_minus(ParamMap ParamMap) throws Exception {
		sqlSession.update("admin.change_goods_att_minus",ParamMap.getMap());
	}

	public void as_final_state(ParamMap ParamMap) throws Exception {
		sqlSession.update("admin.as_final_state",ParamMap.getMap());
	}

	public void change_final_orderState(ParamMap ParamMap) throws Exception {
		sqlSession.update("admin.as_final_state",ParamMap.getMap());
	}

	public void order_list_chagam(ParamMap ParamMap) throws Exception {
		// TODO Auto-generated method stub
		sqlSession.update("admin.order_list_chagam",ParamMap.getMap());
	}

	public void point_chagam(ParamMap ParamMap) throws Exception {
		sqlSession.update("admin.point_chagam",ParamMap.getMap());
	}

	public List<Map<String, Object>> point_total(ParamMap ParamMap) throws Exception {
		return sqlSession.selectList("admin.point_total", ParamMap.getMap());
	}

	public void cashback_final_orderState(ParamMap ParamMap) throws Exception {
		sqlSession.update("admin.cashback_final_orderState",ParamMap.getMap());
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectMemberList(Map<String, Object> map) throws Exception {
		return (List<Map<String, Object>>) selectPagingList("admin.selectMemberList", map);
	}
	
	
}
