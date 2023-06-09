package base.member.my.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import base.common.common.ParamMap;
import base.member.my.dao.MyDAO;


@Service("myService")
public class MyServiceImpl implements MyService {
	
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="myDAO")
	private MyDAO myDAO;
	
	// 구매 목록
	@Override
	public List<Map<String, Object>> orderList(Map<String, Object> map) throws Exception {
		return myDAO.orderList(map);
	}
	
	// 상품명, 상품속성번호 출력
	@Override
	public List<Map<String, Object>> orderList2(Map<String, Object> map) throws Exception {
		return myDAO.orderList2(map);
	}
	
	@Override
	public String pwdCheck(Map<String, Object> map, String id) throws Exception {
		return myDAO.pwdCheck(map, id);
	}

	@Override
	public Map<String, Object> memberModify(String id) throws Exception {
		return myDAO.memberModify(id);
	}
	@Override
	public void memberModifyAction(Map<String, Object> map) throws Exception {
		myDAO.memberModifyAction(map);
	}
	
	@Override
	public void memberDelete(Map<String, Object> map) throws Exception {
		myDAO.memberDelete(map);
	}

	@Override
	public List<Map<String, Object>> myPointList(ParamMap ParamMap) throws Exception {
		return myDAO.myPointList(ParamMap);
	}

	@Override
	public List<Map<String, Object>> myCouponList(ParamMap ParamMap) throws Exception {
		return myDAO.myCouponList(ParamMap);
	}

	@Override
	public List<Map<String, Object>> myLikeList(ParamMap ParamMap) throws Exception {
		return myDAO.myLikeList(ParamMap);
	}

	@Override
	public void goodsLikeDelete(ParamMap ParamMap, HttpServletRequest request) throws Exception {
		myDAO.goodsLikeDelete(ParamMap);
		
	}
	
	@Override
	public List<Map<String, Object>> myDash(ParamMap map) throws Exception {
		// TODO Auto-generated method stub
		return myDAO.myDash(map);		
	}

	@Override
	public List<Map<String, Object>> myGoodsQnaList(ParamMap ParamMap) throws Exception {
		return myDAO.myQnaList(ParamMap);
	}

	@Override
	public List<Map<String, Object>> myReviewList(ParamMap ParamMap) {
		return myDAO.myReviewList(ParamMap);
	}
	
	
	
	
	
}
