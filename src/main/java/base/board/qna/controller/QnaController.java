package base.board.qna.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import base.board.qna.service.QnaService;
import base.common.common.ParamMap;

@Controller
public class QnaController {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="qnaService")
	private QnaService qnaService;
	
	@RequestMapping(value="/board/qna/openQnaList.do")
    public String openQnaList(ParamMap ParamMap) throws Exception{
    	return "/board/qna/qnaList";
    }
	
	@RequestMapping(value="/board/qna/selectQnaList.do")
    public ModelAndView selectQnaList(ParamMap ParamMap) throws Exception{
    	ModelAndView mv = new ModelAndView("jsonView");

    	List<Map<String,Object>> list = qnaService.selectQnaList(ParamMap.getMap());
    	System.out.println(list);
    	mv.addObject("list", list);
    	if(list.size() > 0){
    		mv.addObject("TOTAL", list.get(0).get("TOTAL_COUNT"));
    	}
    	else{
    		mv.addObject("TOTAL", 0);
    	}
    	
    	return mv;
    }
	
	@RequestMapping(value="/board/qna/openQnaWrite.do")
	public String openQnaWrite(ParamMap ParamMap) throws Exception{
		return "/board/qna/qnaWrite";
	}
	
	@RequestMapping(value="/board/qna/insertQna.do", method = RequestMethod.POST )
	public String insertQna(ParamMap ParamMap, HttpServletRequest request) throws Exception{
		qnaService.insertQna(ParamMap.getMap(), request);
		
		return "redirect:/board/qna/openQnaList.do";
	}
	
	@RequestMapping(value="/board/qna/openQnaDetail.do", method = RequestMethod.POST )
	public String openQnaDetail(ParamMap ParamMap, HttpServletRequest request) throws Exception{
		Map<String,Object> map = qnaService.selectQnaDetail(ParamMap.getMap());
		request.setAttribute("map", map.get("map"));
		
		return "/board/qna/qnaDetail";
	}
	
	@RequestMapping(value="/board/qna/openQnaUpdate.do")
	public String openQnaUpdate(ParamMap ParamMap, HttpServletRequest request) throws Exception{
		Map<String,Object> map = qnaService.selectQnaDetail(ParamMap.getMap());
		request.setAttribute("map", map.get("map"));
		request.setAttribute("list", map.get("list"));
		
		return "/board/qna/qnaUpdate";
	}
	
	@RequestMapping(value="/board/qna/updateQna.do")
	public String updateQna(ParamMap ParamMap, HttpServletRequest request) throws Exception{
		qnaService.updateQna(ParamMap.getMap(), request);
		request.setAttribute("QNA_NO", ParamMap.get("QNA_NO"));
		
		return "redirect:/board/qna/openQnaDetail.do";
	}
	
	@RequestMapping(value="/board/qna/deleteQna.do")
	public String deleteQna(ParamMap ParamMap) throws Exception{
		qnaService.deleteQna(ParamMap.getMap());
		
		return "redirect:/board/qna/openQnaList.do";
	}
	
	@ResponseBody
	@RequestMapping(value="/board/qna/chkPassword.do", method = RequestMethod.POST)
	public int chkPassword(@RequestParam Map<String, Object> params) throws Exception{
		int chkPassword = 0;
		Map<String, Object> passwordMap = qnaService.selectQnaPassword(params);
		
		if(String.valueOf(params.get("QNA_PASSWD")).equals(String.valueOf(passwordMap.get("QNA_PASSWD")))){
			chkPassword = 1;
		}
		
		return chkPassword;
	}
	
}
