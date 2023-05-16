package base.board.faq.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import base.board.faq.service.FaqService;
import base.common.common.ParamMap;

@Controller
public class FaqController {
	Logger log = Logger.getLogger(this.getClass());

	@Resource(name="faqService")
	private FaqService faqService;
	
	@RequestMapping(value="/board/faq/openFaqList.do")
    public String openFaqList(ParamMap ParamMap) throws Exception{
    	return "board/faq/faqList.tiles";
    }
	
	@RequestMapping(value="/board/faq/selectFaqList.do")
    public ModelAndView selectFaqList(ParamMap ParamMap) throws Exception{
    	ModelAndView mv = new ModelAndView("jsonView");
    	
    	List<Map<String,Object>> list = faqService.selectFaqList(ParamMap.getMap());
    	mv.addObject("list", list);
    	if(list.size() > 0){
    		mv.addObject("TOTAL", list.get(0).get("TOTAL_COUNT"));
    	}
    	else{
    		mv.addObject("TOTAL", 0);
    	}
    	
    	return mv;
    }
	
	@RequestMapping(value="/board/faq/openFaqWrite.do")
	public String openFaqWrite(ParamMap ParamMap) throws Exception{
		return "board/faq/faqWrite.tiles";
	}
	
	@RequestMapping(value="/board/faq/insertFaq.do")
	public String insertFaq(ParamMap ParamMap, HttpServletRequest request) throws Exception{
		faqService.insertFaq(ParamMap.getMap(), request);
		
		return "redirect:/board/faq/openFaqList.do";
	}
	
	@RequestMapping(value="/board/faq/openFaqDetail.do")
	public String openFaqDetail(ParamMap ParamMap, HttpServletRequest request) throws Exception{
		Map<String,Object> map = faqService.selectFaqDetail(ParamMap.getMap());
		request.setAttribute("map", map.get("map"));
		request.setAttribute("list", map.get("list"));
		
		return "board/faq/faqDetail.tiles";
	}
	
	@RequestMapping(value="/board/faq/openFaqUpdate.do")
	public String openFaqUpdate(ParamMap ParamMap, HttpServletRequest request) throws Exception{
		Map<String,Object> map = faqService.selectFaqDetail(ParamMap.getMap());
		request.setAttribute("map", map.get("map"));
		request.setAttribute("list", map.get("list"));
		
		return "board/faq/faqUpdate.tiles";
	}
	
	@RequestMapping(value="/board/faq/updateFaq.do")
	public String updateFaq(ParamMap ParamMap, HttpServletRequest request) throws Exception{
		faqService.updateFaq(ParamMap.getMap(), request);
		request.setAttribute("NOTICE_NO", ParamMap.get("NOTICE_NO"));
		
		return "redirect:/board/faq/openFaqDetail.do";
	}
	
	@RequestMapping(value="/board/faq/deleteFaq.do")
	public String deleteFaq(ParamMap ParamMap) throws Exception{
		faqService.deleteFaq(ParamMap.getMap());
		
		return "redirect:/board/faq/openFaqList.do";
	}
}
