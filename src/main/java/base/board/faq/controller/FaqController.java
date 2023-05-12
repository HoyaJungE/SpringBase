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
	
	@RequestMapping(value="/faq/openFaqList.do")
    public ModelAndView openFaqList(ParamMap ParamMap) throws Exception{
    	ModelAndView mv = new ModelAndView("/board/faqList");
    	
    	return mv;
    }
	
	@RequestMapping(value="/faq/selectFaqList.do")
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
	
	@RequestMapping(value="/faq/openFaqWrite.do")
	public ModelAndView openFaqWrite(ParamMap ParamMap) throws Exception{
		ModelAndView mv = new ModelAndView("/board/faqWrite");
		
		return mv;
	}
	
	@RequestMapping(value="/faq/insertFaq.do")
	public ModelAndView insertFaq(ParamMap ParamMap, HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/faq/openFaqList.do");
		
		faqService.insertFaq(ParamMap.getMap(), request);
		
		return mv;
	}
	
	@RequestMapping(value="/faq/openFaqDetail.do")
	public ModelAndView openFaqDetail(ParamMap ParamMap) throws Exception{
		ModelAndView mv = new ModelAndView("/board/faqDetail");
		
		Map<String,Object> map = faqService.selectFaqDetail(ParamMap.getMap());
		mv.addObject("map", map.get("map"));
		mv.addObject("list", map.get("list"));
		
		return mv;
	}
	
	@RequestMapping(value="/faq/openFaqUpdate.do")
	public ModelAndView openFaqUpdate(ParamMap ParamMap) throws Exception{
		ModelAndView mv = new ModelAndView("/board/faqUpdate");
		
		Map<String,Object> map = faqService.selectFaqDetail(ParamMap.getMap());
		mv.addObject("map", map.get("map"));
		mv.addObject("list", map.get("list"));
		
		return mv;
	}
	
	@RequestMapping(value="/faq/updateFaq.do")
	public ModelAndView updateFaq(ParamMap ParamMap, HttpServletRequest request) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/faq/openFaqDetail.do");
		
		faqService.updateFaq(ParamMap.getMap(), request);
		
		mv.addObject("NOTICE_NO", ParamMap.get("NOTICE_NO"));
		return mv;
	}
	
	@RequestMapping(value="/faq/deleteFaq.do")
	public ModelAndView deleteFaq(ParamMap ParamMap) throws Exception{
		ModelAndView mv = new ModelAndView("redirect:/faq/openFaqList.do");
		
		faqService.deleteFaq(ParamMap.getMap());
		
		return mv;
	}
}
