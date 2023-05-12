package base.shop.order.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import base.common.common.ParamMap;

public interface OrderService {

	Map<String, Object> orderMemberInfo(ParamMap ParamMap, HttpServletRequest request) throws Exception;

	List<Map<String, Object>> memberCoupon(ParamMap ParamMap) throws Exception;

	void insertOrder(ParamMap ParamMap, HttpServletRequest request) throws Exception;

	Map<String, Object> selectOrder(ParamMap ParamMap, HttpServletRequest request) throws Exception;

	void orderModify(ParamMap ParamMap, HttpServletRequest request) throws Exception;

	void updateMember(ParamMap ParamMap, HttpServletRequest request) throws Exception;

	


}