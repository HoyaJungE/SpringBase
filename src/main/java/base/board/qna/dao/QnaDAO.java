package base.board.qna.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import base.common.dao.AbstractDao;

@Repository("qnaDAO")
public class QnaDAO extends AbstractDao{

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectQnaList(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>)selectPagingList("qna.selectQnaList", map);
	}

	public void insertQna(Map<String, Object> map) throws Exception{
		sqlSession.insert("qna.insertQna", map);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> selectQnaDetail(Map<String, Object> map) throws Exception{
		return sqlSession.selectOne("qna.selectQnaDetail", map);
	}

	public void updateQna(Map<String, Object> map) throws Exception{
		sqlSession.update("qna.updateQna", map);
	}

	public void deleteQna(Map<String, Object> map) throws Exception{
		sqlSession.update("qna.deleteQna", map);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> selectQnaPassword(Map<String, Object> map) {
		return sqlSession.selectOne("qna.selectQnaPassword", map);
	}

}
