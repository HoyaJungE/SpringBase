package base.menu.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface MenuService {

	List<Map<String, Object>> selectMenuList(Map<String, Object> map) throws Exception;

	void insertMenu(Map<String, Object> map, HttpServletRequest request) throws Exception;

	Map<String, Object> selectMenuDetail(Map<String, Object> map) throws Exception;

	void updateMenu(Map<String, Object> map, HttpServletRequest request) throws Exception;

	void deleteMenu(Map<String, Object> map) throws Exception;

}
