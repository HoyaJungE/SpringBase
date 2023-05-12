package base.member.my.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import base.common.common.ParamMap;
import base.common.dao.AbstractDao;


@Repository("myDAO")
public class MyDAO extends AbstractDao {
	
	// 구매 목록
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> orderList(Map<String,Object> map) { //  
		return sqlSession.selectList("my.selectOrderList",map);
	}
	// 상품명, 상품속성번호
	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> orderList2(Map<String,Object> map) { //  
		return sqlSession.selectList("my.selectOrderList2",map);
	}
	// 회원 정보 수정
	public String pwdCheck(Map<String, Object> map, String id) throws Exception{
		return sqlSession.selectOne("my.pwdCheck", map);
	}
	
	@SuppressWarnings("unchecked")
	public Map<String,Object> memberModify(String id) throws Exception {
		return sqlSession.selectOne("my.memberModify",id);
	}
	
	public void memberModifyAction(Map<String, Object> map) throws Exception {
		sqlSession.update("my.memberModifyAction", map);
	}
	
	public void memberDelete(Map<String, Object> map) throws Exception {
		sqlSession.update("my.memberDelete", map);
	}
	

	public List<Map<String, Object>> myPointList(ParamMap ParamMap) {
		return sqlSession.selectList("my.myPointList",ParamMap.getMap());
	}
	public List<Map<String, Object>> myCouponList(ParamMap ParamMap) {
		return sqlSession.selectList("my.myCouponList",ParamMap.getMap());
	}
	public List<Map<String, Object>> myLikeList(ParamMap ParamMap) {
		return sqlSession.selectList("my.myLikeList",ParamMap.getMap());
	}
	public void goodsLikeDelete(ParamMap ParamMap) {
		sqlSession.delete("goods.deleteGoodsLike", ParamMap.getMap());
	}

	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> myDash(ParamMap map) throws Exception { //adminMain대쉬보드 
		return sqlSession.selectList("my.my_side",map);
	}
	
	public List<Map<String, Object>> myQnaList(ParamMap ParamMap) {
		return sqlSession.selectList("my.myGoodsQnaList",ParamMap.getMap());
	}
	
	public List<Map<String, Object>> myReviewList(ParamMap ParamMap) {
		return sqlSession.selectList("my.myReviewList",ParamMap.getMap());
	}


}
