package base.menu.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import base.common.dao.AbstractDao;

@Repository("MenuDAO")
public class MenuDAO extends AbstractDao{

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectMenuList(Map<String, Object> map) throws Exception{
		return sqlSession.selectList("menu.selectMenuList", map);
	}

	public void insertMenu(Map<String, Object> map) throws Exception{
		sqlSession.insert("menu.insertMenu", map);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> selectMenuDetail(Map<String, Object> map) throws Exception{
		return sqlSession.selectOne("menu.selectMenuDetail", map);
	}

	public void updateMenu(Map<String, Object> map) throws Exception{
		sqlSession.update("menu.updateMenu", map);
	}

	public void deleteMenu(Map<String, Object> map) throws Exception{
		sqlSession.update("menu.deleteMenu", map);
	}

}
