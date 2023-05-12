package base.shop.basket.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import base.common.common.ParamMap;
import base.shop.basket.dao.BasketDAO;
import base.shop.goods.dao.GoodsDAO;




@Service("basketService")
public class BasketServiceImpl implements BasketService{
	
Logger log = Logger.getLogger(this.getClass()); // 로그
	
	@Resource(name="basketDao") 
	private BasketDAO basketDao;

	@Override
	public List<Map<String, Object>> basketList(ParamMap ParamMap) throws Exception {
		return (List<Map<String, Object>>) basketDao.basketList(ParamMap);
	}

	@Override
	public void basketModify(ParamMap ParamMap, HttpServletRequest request) throws Exception {
		basketDao.basketModify(ParamMap);
		
	}

	@Override
	public void basketDelete(ParamMap ParamMap, HttpServletRequest request) throws Exception {
		basketDao.basketDelete(ParamMap);
		
	}

	@Override
	public void basketAllDelete(ParamMap ParamMap, HttpServletRequest request) throws Exception {
		basketDao.basketAllDelete(ParamMap);
	}

	@Override
	public Map<String, Object> selectGoodsLike(ParamMap ParamMap, HttpServletRequest request) throws Exception {
		return basketDao.selectGoodsLike(ParamMap);
	}

	@Override
	public void insertGoodsLike(ParamMap ParamMap, HttpServletRequest request) throws Exception {
		
		basketDao.insertGoodsLike(ParamMap);
		
	}

	@Override
	public List<Map<String, Object>> basketSelectList(ParamMap ParamMap, HttpServletRequest request) throws Exception {
		return (List<Map<String, Object>>) basketDao.basketSelectList(ParamMap);
	}

	
	

	

}
