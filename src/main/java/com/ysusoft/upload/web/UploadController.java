package com.ysusoft.upload.web;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.ysusoft.frame.sys.base.bean.ResultInfo;
import com.ysusoft.frame.sys.base.bean.UploadSetting;
import com.ysusoft.upload.util.UploadImg;

@Controller
@RequestMapping("uploadweb")
public class UploadController {

	private static String imgServerUrl = UploadSetting.getSavePath();
/**
 * 上传图片
 * @param request
 * @param midiaServerUrl
 * @return
 */
@RequestMapping(value="imgupload",method=RequestMethod.POST)
@ResponseBody
public ResultInfo<UploadImg> uploadImg(HttpServletRequest request,@RequestParam(value="file", required=false) MultipartFile file) throws IOException{
	ResultInfo<UploadImg> rs = new ResultInfo<UploadImg>();
	String newFileName= "default.jpg";
	byte[] bytes = file.getBytes();
	try {
		 newFileName=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date())+".jpg";
		 String uploadDir =imgServerUrl;
		 File dirPath = new File(uploadDir);
		 if (!dirPath.exists()) {  
		     dirPath.mkdirs();  
		 } 
		 File uploadedFile = new File(uploadDir + newFileName);  
		 FileCopyUtils.copy(bytes, uploadedFile);  
		 rs.setMessage(newFileName);
		 rs.setSuccess(true);
	} catch (Exception e) {
		System.out.println("发生异常：" + e.getMessage());  
	}  
	return rs;
}

	
}
