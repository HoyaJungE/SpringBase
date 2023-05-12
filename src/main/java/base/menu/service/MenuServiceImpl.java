package base.menu.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import base.menu.dao.MenuDAO;



@Service("MenuService")
public class MenuServiceImpl implements MenuService{
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="MenuDAO")
	private MenuDAO menuDAO;
	
	@Override
	public List<Map<String, Object>> selectMenuList(Map<String, Object> map) throws Exception {
		return menuDAO.selectMenuList(map);
	}

	@Override
	public void insertMenu(Map<String, Object> map, HttpServletRequest request) throws Exception {
		menuDAO.insertMenu(map);		
	}

	@Override
	public Map<String, Object> selectMenuDetail(Map<String, Object> map) throws Exception {
		return menuDAO.selectMenuDetail(map);
	}

	@Override
	public void updateMenu(Map<String, Object> map, HttpServletRequest request) throws Exception{
		menuDAO.updateMenu(map);
	}

	@Override
	public void deleteMenu(Map<String, Object> map) throws Exception {
		menuDAO.deleteMenu(map);
	}

}
