package base.shop.basket.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import base.common.common.CommandMap;
import base.common.dao.AbstractDao;

@Repository("basketDao")
public class BasketDAO extends AbstractDao{

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> basketList(CommandMap commandMap) throws Exception{
		return sqlSession.selectList("basket.basketList", commandMap.getMap());
	}
	
	public void basketModify(CommandMap commandMap) throws Exception{
		sqlSession.update("basket.basketModify", commandMap.getMap());
	}
	
	public void basketDelete(CommandMap commandMap) throws Exception{
		sqlSession.delete("basket.basketDelete", commandMap.getMap());
		
	}

	public void basketAllDelete(CommandMap commandMap) throws Exception{
		sqlSession.delete("basket.basketAllDelete", commandMap.getMap());
		
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> selectGoodsLike(CommandMap commandMap) throws Exception{
		return sqlSession.selectOne("basket.selectGoodsLike", commandMap.getMap());
	}

	public void insertGoodsLike(CommandMap commandMap) {
		sqlSession.insert("basket.insertGoodsLike", commandMap.getMap());
		
	}

	public List<Map<String, Object>> basketSelectList(CommandMap commandMap) {
		return sqlSession.selectList("basket.basketSelectList", commandMap.getMap());
	}


	
	
	
	


	
	
	

}
