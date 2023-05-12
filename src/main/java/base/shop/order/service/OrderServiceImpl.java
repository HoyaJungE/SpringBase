package base.shop.order.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import base.common.common.ParamMap;
import base.shop.order.dao.OrderDAO;

@Service("orderService")
public class OrderServiceImpl implements OrderService{
Logger log = Logger.getLogger(this.getClass()); // 로그
	
	@Resource(name="orderDao") 
	private OrderDAO orderDao;

	//주문페이지에서 필요한 정보 검색
	@Override
	public Map<String, Object> orderMemberInfo(ParamMap ParamMap, HttpServletRequest request) throws Exception {
		return (Map<String, Object>) orderDao.orderMemberInfo(ParamMap);
	}

	//해당 회원이 보유한 쿠폰 검색
	@Override
	public List<Map<String, Object>> memberCoupon(ParamMap ParamMap) throws Exception {
		return (List<Map<String, Object>>) orderDao.memberCoupon(ParamMap);
	}

	//주문테이블 입력
	@Override
	public void insertOrder(ParamMap ParamMap, HttpServletRequest request) throws Exception {
		orderDao.insertOrder(ParamMap);
	}
	
	//입력한 주문 번호검색(주문완료 페이지에서 주문번호 보여주기)
	@Override public Map<String, Object> selectOrder(ParamMap ParamMap, HttpServletRequest request) throws Exception { 
		return (Map<String, Object>) orderDao.selectOrder(ParamMap); 
	}

	@Override
	public void orderModify(ParamMap ParamMap, HttpServletRequest request) throws Exception {
		orderDao.orderModify(ParamMap);
		
	}

	@Override
	public void updateMember(ParamMap ParamMap, HttpServletRequest request) throws Exception {
		orderDao.updateMember(ParamMap);
		
	}
	
	
	
	
	  
	 
	
}
