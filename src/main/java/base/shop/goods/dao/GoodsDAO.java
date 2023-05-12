package base.shop.goods.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import base.common.common.ParamMap;
import base.common.dao.AbstractDao;

@Repository("goodsDao")
public class GoodsDAO extends AbstractDao {
	
	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> newGoodsList(Map<String, Object> map) throws Exception { // 최신 상품 리스트
		return sqlSession.selectList("goods.newGoodsList",map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> bestGoodsList(Map<String, Object> map) throws Exception { // 베스트 상품 리스트
		return sqlSession.selectList("goods.bestGoodsList", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> cateGoodsList(Map<String, Object> map) throws Exception { // 카테고리 별 상품 리스트
		return sqlSession.selectList("goods.cateGoodsList", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> mainSearch(Map<String, Object> map) throws Exception { // 메인에서 검색
		return sqlSession.selectList("goods.mainSearch", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectBasketNo(Map<String, Object> map) throws Exception { // 장바구니 시퀀스번호 가져오기
		return sqlSession.selectList("goods.selectBasketNo", map);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> selectGoodsDetail(Map<String, Object> map) throws Exception { // 상품 디테일 데이터 가져오기
		return sqlSession.selectOne("goods.selectGoodsDetail", map);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> selectGoodsAtt(Map<String, Object> map) throws Exception { // 상품옵션 컬러 사이즈 배열 가져오기
		return sqlSession.selectOne("goods.selectGoodsAtt", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectGoodsQna(Map<String, Object> map) throws Exception { // 상품 QNA 리스트
		return sqlSession.selectList("goods.selectGoodsQna", map);
	}

	@SuppressWarnings("unchecked")
	public List<Map<String, Object>> selectReviewList(Map<String, Object> map) throws Exception { // 상품 Review 리스트 
		System.out.println("selectReviewList=" + map);
		return sqlSession.selectList("goods.selectReviewList", map);
	}
	
	public void updateReview(Map<String, Object> map) throws Exception { // 상품 Review 수정
		sqlSession.update("goods.updateReview", map);
	}

	public void goodsHitCnt(Map<String, Object> map) throws Exception { // 조회수 증가
		sqlSession.update("goods.goodsReadCntUp", map);
	}

	public void insertGoods(Map<String, Object> map) throws Exception { // 상품 등록
		sqlSession.insert("goods.goodsInsert", map);
	}

	public void updateGoods(Map<String, Object> map) throws Exception { // 상품 수정
		sqlSession.update("goods.updateGoods", map);
	}
	
	public void deleteGoods(Map<String, Object> map) throws Exception { // 상품 삭제(숨김)
		sqlSession.update("goods.deleteGoods", map);
	}

	public void updateGoodsThumbnail(Map<String, Object> map) throws Exception { // 상품 썸네일 이미지 수정
		sqlSession.update("goods.updateGoodsThumbnail", map);
	}

	public void goodsAttribute(Map<String, Object> map) throws Exception { // 상품옵션 등록
		sqlSession.insert("goods.attributeInsert", map);
	}

	public void attributeDelete(Map<String, Object> map) throws Exception { // 상품옵션 삭제
		sqlSession.insert("goods.attributeDelete", map);
	}

	public void insertGoodsThumbnail(Map<String, Object> map) throws Exception { // 상품 썸네일 이미지 등록
		sqlSession.update("goods.updateGoodsThumbnail", map);
	}

	public void insertFile(Map<String, Object> map) throws Exception { // 상품 이미지파일 등록
		sqlSession.insert("goods.insertFile", map);
	}

	public void insertReviewFile(Map<String, Object> map) throws Exception { // 리뷰 이미지파일 등록
		sqlSession.insert("goods.insertReviewFile", map);
	}

	public void deleteFile(Map<String, Object> map) throws Exception { // 이미지 파일 삭제
		sqlSession.insert("goods.deleteFile", map);
	}

	public void insertGoodsLike(Map<String, Object> map) throws Exception { // 상품 디테일에서 좋아요 등록
		sqlSession.insert("goods.insertGoodsLike", map);
	}

	public void deleteGoodsLike(Map<String, Object> map) throws Exception { // 상품 디테일에서 좋아요 삭제
		sqlSession.delete("shop.deleteGoodsLike", map);
	}

	public void insertBasket(Map<String, Object> map) throws Exception { // 상품 디테일에서 장바구니 등록
		sqlSession.insert("goods.insertBasket", map);
	}

	@SuppressWarnings("unchecked")
	public Map<String, Object> selectGoodsAttNum(Map<String, Object> map) throws Exception { // 상품 옵션 PK값 가져오기
		return sqlSession.selectOne("goods.selectGoodsAttNum", map);
	}

	public void insertGoodsQna(Map<String, Object> map) throws Exception { // 상품 QNA 등록
		sqlSession.insert("goods.insertGoodsQna", map);
	}
	
	public void updateGoodsQna(Map<String, Object> map) throws Exception { // 상품 QNA 답변수정
		sqlSession.update("goods.updateGoodsQna", map);
	}

	public void insertGoodsReview(Map<String, Object> map) throws Exception { // 상품 Review 등록
		sqlSession.insert("goods.insertGoodsReview", map);
	}
	
	public void gumeListDelete(Map<String, Object> map) throws Exception { // 구매 리스트 초기화
		sqlSession.delete("goods.gumeListDelete", map);
	}

}
