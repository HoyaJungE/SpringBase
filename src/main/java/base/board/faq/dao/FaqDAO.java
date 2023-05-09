package base.board.faq.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import base.common.dao.AbstractDao;

@Repository("faqDAO")
public class FaqDAO extends AbstractDao{

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectFaqList(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>)selectPagingList("faq.selectFaqList", map);
	}

	public void insertFaq(Map<String, Object> map) throws Exception{
		sqlSession.insert("faq.insertFaq", map);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> selectFaqDetail(Map<String, Object> map) throws Exception{
		return sqlSession.selectOne("faq.selectFaqDetail", map);
	}

	public void updateFaq(Map<String, Object> map) throws Exception{
		sqlSession.update("faq.updateFaq", map);
	}

	public void deleteFaq(Map<String, Object> map) throws Exception{
		sqlSession.update("faq.deleteFaq", map);
	}

}
