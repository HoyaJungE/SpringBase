package base.member.my.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import base.common.common.CommandMap;
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
	

	public List<Map<String, Object>> myPointList(CommandMap commandMap) {
		return sqlSession.selectList("my.myPointList",commandMap.getMap());
	}
	public List<Map<String, Object>> myCouponList(CommandMap commandMap) {
		return sqlSession.selectList("my.myCouponList",commandMap.getMap());
	}
	public List<Map<String, Object>> myLikeList(CommandMap commandMap) {
		return sqlSession.selectList("my.myLikeList",commandMap.getMap());
	}
	public void goodsLikeDelete(CommandMap commandMap) {
		sqlSession.delete("goods.deleteGoodsLike", commandMap.getMap());
	}

	@SuppressWarnings("unchecked")
	public List<Map<String,Object>> myDash(CommandMap map) throws Exception { //adminMain대쉬보드 
		return sqlSession.selectList("my.my_side",map);
	}
	
	public List<Map<String, Object>> myQnaList(CommandMap commandMap) {
		return sqlSession.selectList("my.myGoodsQnaList",commandMap.getMap());
	}
	
	public List<Map<String, Object>> myReviewList(CommandMap commandMap) {
		return sqlSession.selectList("my.myReviewList",commandMap.getMap());
	}


}
