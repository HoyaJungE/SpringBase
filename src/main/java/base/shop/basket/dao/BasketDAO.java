package base.shop.basket.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import base.common.common.ParamMap;
import base.common.dao.AbstractDao;

@Repository("basketDao")
public class BasketDAO extends AbstractDao{

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> basketList(ParamMap ParamMap) throws Exception{
		return sqlSession.selectList("basket.basketList", ParamMap.getMap());
	}
	
	public void basketModify(ParamMap ParamMap) throws Exception{
		sqlSession.update("basket.basketModify", ParamMap.getMap());
	}
	
	public void basketDelete(ParamMap ParamMap) throws Exception{
		sqlSession.delete("basket.basketDelete", ParamMap.getMap());
		
	}

	public void basketAllDelete(ParamMap ParamMap) throws Exception{
		sqlSession.delete("basket.basketAllDelete", ParamMap.getMap());
		
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> selectGoodsLike(ParamMap ParamMap) throws Exception{
		return sqlSession.selectOne("basket.selectGoodsLike", ParamMap.getMap());
	}

	public void insertGoodsLike(ParamMap ParamMap) {
		sqlSession.insert("basket.insertGoodsLike", ParamMap.getMap());
		
	}

	public List<Map<String, Object>> basketSelectList(ParamMap ParamMap) {
		return sqlSession.selectList("basket.basketSelectList", ParamMap.getMap());
	}


	
	
	
	


	
	
	

}
