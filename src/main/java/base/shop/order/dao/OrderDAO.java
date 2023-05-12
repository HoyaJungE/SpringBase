package base.shop.order.dao;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import base.common.common.ParamMap;
import base.common.dao.AbstractDao;

@Repository("orderDao")
public class OrderDAO extends AbstractDao{
	
	@SuppressWarnings("unchecked")
	public Map<String, Object> orderMemberInfo(ParamMap ParamMap) throws Exception{
		return sqlSession.selectOne("order.orderMemberInfo", ParamMap.getMap());
	}

	public List<Map<String, Object>> memberCoupon(ParamMap ParamMap) {
		return sqlSession.selectList("order.memberCoupon", ParamMap.getMap());
	}

	public void insertOrder(ParamMap ParamMap) throws Exception{
		sqlSession.insert("order.insertOrder", ParamMap.getMap()); //ORDER테이블에 INSERT
		//주문한 상품의 종류가 한개일때
		if(ParamMap.get("goods_no").getClass().getName().equals("java.lang.String")){ 
		  Map<String,Object> dp = new HashMap<String, Object>(); 
		  dp.put("GOODS_NO",ParamMap.get("goods_no")); 
		  dp.put("GOODS_ATT_NO",ParamMap.get("goods_att_no")); 
		  dp.put("ORDER_DETAIL_PRICE",ParamMap.get("ORDER_DETAIL_PRICE")); 
		  dp.put("ORDER_DETAIL_COLOR", ParamMap.get("goods_att_color")); 
		  dp.put("ORDER_DETAIL_SIZE", ParamMap.get("goods_att_size")); 
		  dp.put("ORDER_DETAIL_AMOUNT",ParamMap.get("basket_goods_amount")); 
		  dp.put("COUPON_DISCOUNT",ParamMap.get("COUPON_DISCOUNT")); 
		  dp.put("ORDER_DISCOUNT_APPLY",ParamMap.get("ORDER_DISCOUNT_APPLY")); 
		  dp.put("COUPON_NO",ParamMap.get("COUPON_NO_1")); 
		  dp.put("MEMBER_NO", ParamMap.get("MEMBER_NO"));
		  sqlSession.insert("order.insertOrderDetail", dp);
		  sqlSession.update("goods.updateGoodsAmount", dp);
		  sqlSession.update("goods.updateGoodsDisplay", dp);
		  
		  Map<String,Object> bod = new HashMap<String, Object>();
		  bod.put("BASKET_NO", ParamMap.get("basket_no")); 
		  bod.put("GOODS_ATT_NO", ParamMap.get("goods_att_no")); 
		  bod.put("MEMBER_NO", ParamMap.get("MEMBER_NO"));
		  sqlSession.delete("basket.basketOrderDelete", bod);
		}else {	 //주문한 상품의 종류가 두개 이상일때
			String[] GOODS_NO = (String[])ParamMap.getMap().get("goods_no");
			String[] GOODS_ATT_NO = (String[])ParamMap.getMap().get("goods_att_no");
			String[] ORDER_DETAIL_PRICE = (String[])ParamMap.getMap().get("ORDER_DETAIL_PRICE");
			String[] ORDER_DETAIL_COLOR = (String[])ParamMap.getMap().get("goods_att_color");
			String[] ORDER_DETAIL_SIZE = (String[])ParamMap.getMap().get("goods_att_size");
			String[] ORDER_DETAIL_AMOUNT = (String[])ParamMap.getMap().get("basket_goods_amount");
			String[] COUPON_DISCOUNT = (String[])ParamMap.getMap().get("COUPON_DISCOUNT");
			String[] ORDER_DISCOUNT_APPLY = (String[])ParamMap.getMap().get("ORDER_DISCOUNT_APPLY");
			String[] BASKET_NO = (String[])ParamMap.getMap().get("basket_no");
			String COUPON_NO = (String) ParamMap.getMap().get("COUPON_NO_1");
		
			String a=Arrays.toString(BASKET_NO).replace("[", "");
			a=a.replace("]", "");
			String b=Arrays.toString(GOODS_ATT_NO).replace("[", "");
			b=b.replace("]", "");
		
			int len = ORDER_DISCOUNT_APPLY.length;
			for(int i=0; i<len; i++ ) { 
			  Map<String,Object> dp = new HashMap<String, Object>();
			  dp.put("GOODS_NO", GOODS_NO[i]);
			  dp.put("GOODS_ATT_NO", GOODS_ATT_NO[i]);
			  dp.put("ORDER_DETAIL_PRICE", ORDER_DETAIL_PRICE[i]);
			  dp.put("ORDER_DETAIL_COLOR", ORDER_DETAIL_COLOR[i]);
			  dp.put("ORDER_DETAIL_SIZE", ORDER_DETAIL_SIZE[i]);
			  dp.put("ORDER_DETAIL_AMOUNT", ORDER_DETAIL_AMOUNT[i]);
			  dp.put("COUPON_DISCOUNT", COUPON_DISCOUNT[i]);
			  dp.put("ORDER_DISCOUNT_APPLY", ORDER_DISCOUNT_APPLY[i]);
			  dp.put("COUPON_NO", COUPON_NO);
			  dp.put("MEMBER_NO", ParamMap.get("MEMBER_NO"));
			  sqlSession.insert("order.insertOrderDetail", dp); 
			  sqlSession.update("goods.updateGoodsAmount", dp);
			  sqlSession.update("goods.updateGoodsDisplay", dp);
			}
			Map<String,Object> bod = new HashMap<String, Object>();
			bod.put("MEMBER_NO", ParamMap.get("MEMBER_NO"));
		 	bod.put("BASKET_NO", a);
			bod.put("GOODS_ATT_NO", b);
			sqlSession.delete("basket.basketOrderDelete", bod);
		}
		//사용한 포인트가 있다면 사용포인트 INSERT
		if(!ParamMap.get("ORDER_USE_POINT").equals("0")) {
			sqlSession.insert("point.usePoint", ParamMap.getMap()); 
	      }
		sqlSession.insert("point.savePoint", ParamMap.getMap());	//포인트 적립 INSERT
		//사용한 쿠폰이 있다면
		if(!ParamMap.get("COUPON_STATUS_NO_1").equals("")) {
			sqlSession.update("coupon.useCoupon", ParamMap.getMap());
		}
	}
	
	 @SuppressWarnings("unchecked") 
	 public Map<String, Object> selectOrder(ParamMap ParamMap) { 
		 return sqlSession.selectOne("order.selectOrder", ParamMap.getMap()); 
	 }

	public void orderModify(ParamMap ParamMap) {
		sqlSession.update("order.orderModify", ParamMap.getMap());
	}

	public void updateMember(ParamMap ParamMap) {
		sqlSession.update("join.updateMemberTotal", ParamMap.getMap());
		
		Map<String,Object> map = sqlSession.selectOne("join.selectMemberTotal", ParamMap.getMap());
		
		int MEMBER_TOTAL = Integer.parseInt(map.get("MEMBER_TOTAL").toString());
		String MEMBER_GRADE = "";
		
		if(MEMBER_TOTAL<200000) {
			MEMBER_GRADE = "NORMAL";
		}else if(MEMBER_TOTAL>=200000 && MEMBER_TOTAL<500000) {
			MEMBER_GRADE = "GOLD";
		}else {
			MEMBER_GRADE = "VIP";
		}
		Map<String,Object> mg = new HashMap<String, Object>(); 
		mg.put("MEMBER_GRADE", MEMBER_GRADE);
		mg.put("MEMBER_NO", ParamMap.get("MEMBER_NO"));
		sqlSession.update("join.updateMemberGrade", mg);
	}
	  
	 
	
	
	
}
