package base.member.join.dao;

import java.util.Map;


import org.springframework.stereotype.Repository;

import base.common.dao.AbstractDao;

@Repository("joinDAO")
public class JoinDAO extends AbstractDao {
	
	//
	public void insertMember(Map<String, Object> map) throws Exception {
		sqlSession.insert("join.insertMember", map);
		sqlSession.insert("join.insertDefaultPoint", map);
	}
	
	public int selectIdCheck(String MEMBER_ID) throws Exception{
		return sqlSession.selectOne("join.selectIdCheck", MEMBER_ID);
	}
	
	public int selectEmailCheck(String MEMBER_EMAIL) throws Exception{
		return sqlSession.selectOne("join.selectEmailCheck", MEMBER_EMAIL);
	}
}
