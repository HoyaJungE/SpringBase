package base.board.notice.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import base.common.dao.AbstractDao;

@Repository("noticeDAO")
public class NoticeDAO extends AbstractDao{

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectNoticeList(Map<String, Object> map) throws Exception{
		return (List<Map<String, Object>>)selectPagingList("notice.selectNoticeList", map);
	}

	public void insertNotice(Map<String, Object> map) throws Exception{
		sqlSession.insert("notice.insertNotice", map);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> selectNoticeDetail(Map<String, Object> map) throws Exception{
		return sqlSession.selectOne("notice.selectNoticeDetail", map);
	}

	public void updateNotice(Map<String, Object> map) throws Exception{
		sqlSession.update("notice.updateNotice", map);
	}

	public void deleteNotice(Map<String, Object> map) throws Exception{
		sqlSession.update("notice.deleteNotice", map);
	}

}
