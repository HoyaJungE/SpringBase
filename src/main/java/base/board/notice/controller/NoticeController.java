package base.board.notice.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import base.board.notice.service.NoticeService;
import base.common.common.ParamMap;

@Controller
public class NoticeController {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="noticeService")
	private NoticeService noticeService;
	
	@RequestMapping(value="/board/notice/openNoticeList.do")
    public String openNoticeList(ParamMap ParamMap) throws Exception{
    	return "/board/notice/noticeList";
    }
	
	@RequestMapping(value="/board/notice/selectNoticeList.do")
    public ModelAndView selectNoticeList(ParamMap ParamMap) throws Exception{
    	ModelAndView mv = new ModelAndView("jsonView");
    	
    	List<Map<String,Object>> list = noticeService.selectNoticeList(ParamMap.getMap());
    	mv.addObject("list", list);
    	if(list.size() > 0){
    		mv.addObject("TOTAL", list.get(0).get("TOTAL_COUNT"));
    	}
    	else{
    		mv.addObject("TOTAL", 0);
    	}
    	
    	return mv;
    }
	
	@RequestMapping(value="/board/notice/openNoticeWrite.do")
	public String openNoticeWrite(ParamMap ParamMap) throws Exception{
		return "/board/notice/noticeWrite";
	}
	
	@RequestMapping(value="/board/notice/insertNotice.do")
	public String insertNotice(ParamMap ParamMap, HttpServletRequest request) throws Exception{
		noticeService.insertNotice(ParamMap.getMap(), request);
		
		return "redirect:/board/notice/openNoticeList.do";
	}
	
	@RequestMapping(value="/board/notice/openNoticeDetail.do")
	public String openNoticeDetail(ParamMap ParamMap, HttpServletRequest request) throws Exception{
		Map<String,Object> map = noticeService.selectNoticeDetail(ParamMap.getMap());
		request.setAttribute("map", map.get("map"));
		request.setAttribute("list", map.get("list"));
		
		return "/board/notice/noticeDetail";
	}
	
	@RequestMapping(value="/board/notice/openNoticeUpdate.do")
	public String openNoticeUpdate(ParamMap ParamMap, HttpServletRequest request) throws Exception{
		Map<String,Object> map = noticeService.selectNoticeDetail(ParamMap.getMap());
		request.setAttribute("map", map.get("map"));
		request.setAttribute("list", map.get("list"));
		
		return "/board/notice/noticeUpdate";
	}
	
	@RequestMapping(value="/board/notice/updateNotice.do")
	public String updateNotice(ParamMap ParamMap, HttpServletRequest request) throws Exception{
		noticeService.updateNotice(ParamMap.getMap(), request);
		request.setAttribute("NOTICE_NO", ParamMap.get("NOTICE_NO"));
		
		return "redirect:/board/notice/openNoticeDetail.do";
	}
	
	@RequestMapping(value="/board/notice/deleteNotice.do")
	public String deleteNotice(ParamMap ParamMap) throws Exception{
		noticeService.deleteNotice(ParamMap.getMap());
		
		return "redirect:/board/notice/openNoticeList.do";
	}
}
