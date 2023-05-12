package base.commom.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import base.common.common.ParamMap;
import base.common.service.CommonService;



@Controller
public class CommonController {
	Logger log = Logger.getLogger(this.getClass());
	
	@Resource(name="commonService")
	private CommonService commonService;
	
	@RequestMapping(value="/common/downloadFile.do")
	public void downloadFile(ParamMap ParamMap, HttpServletResponse response) throws Exception{
		Map<String,Object> map = commonService.selectFileInfo(ParamMap.getMap());
		String storedFileName = (String)map.get("UPLOAD_SAVE_NAME");
		String originalFileName = (String)map.get("UPLOAD_ORIGIN_NAME");
		
		byte fileByte[] = FileUtils.readFileToByteArray(new File("D:\\sts4File\\"+storedFileName));
		
		response.setContentType("application/octet-stream");
		response.setContentLength(fileByte.length);
		response.setHeader("Content-Disposition", "attachment; fileName=\"" + URLEncoder.encode(originalFileName,"UTF-8")+"\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.getOutputStream().write(fileByte);
		
		response.getOutputStream().flush();
		response.getOutputStream().close();
	}
}
