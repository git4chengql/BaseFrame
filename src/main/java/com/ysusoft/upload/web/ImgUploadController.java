package com.ysusoft.upload.web;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.MultipartConfigElement;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.ysusoft.frame.sys.base.bean.UploadSetting;
import com.ysusoft.upload.util.UploadUtils;

@Controller
@RequestMapping(value="upload/")
@Configuration
@ImportResource(locations={"classpath:upload.xml"})
public class ImgUploadController {
	
	@Bean
	 MultipartConfigElement multipartConfigElement() {
	    MultipartConfigFactory factory = new MultipartConfigFactory();
	    factory.setLocation("/app/pttms/tmp");
	    return factory.createMultipartConfig();
	}
	/**
	 * 上传图片
	 * @param request
	 * @param midiaServerUrl
	 * @return
	 */
	@RequestMapping(value="imgupload",method=RequestMethod.POST)
	@ResponseBody
	public String uploadImg(HttpServletRequest request)throws IOException{
		Map<String,Object> data = new HashMap<String,Object>();
		String newFileName= "default.jpg";
		try {
			 newFileName=new SimpleDateFormat("yyyyMMddhhmmss").format(new Date())+".jpg";
			 File  file =new File(newFileName);
			 MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			 MultipartFile mfile = multipartRequest.getFile("file");
			 CommonsMultipartFile cf= (CommonsMultipartFile)mfile;
			 DiskFileItem fi = (DiskFileItem)cf.getFileItem();
			 file = fi.getStoreLocation(); 
			 data.put("imgName", newFileName);
			 if(UploadUtils.uploadImg(UploadSetting.getQhdimgServerUrl(),file, newFileName)){
			 }
		} catch (Exception e) {
		}  
		
		System.out.println("上传成功名字是："+newFileName);
		return newFileName;
	}
	
	@RequestMapping(value="uploadaudio",method=RequestMethod.POST)
	@ResponseBody
	public String uploadAudio(HttpServletRequest request){
		Map<String,Object> data = new HashMap<String,Object>();
		String newFileName= "default.mp3";
		try {
			 newFileName=new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date())+".mp3";
			 File file = new File(newFileName);
			 MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			 MultipartFile mfile = multipartRequest.getFile("file");
			 CommonsMultipartFile cf= (CommonsMultipartFile)mfile;
			 DiskFileItem fi = (DiskFileItem)cf.getFileItem();
			file = fi.getStoreLocation(); 
			 data.put("audioName", newFileName);
			 if(UploadUtils.uploadImg(UploadSetting.getQhdaudioServerUrl(),file, newFileName)){
			 }
		} catch (Exception e) {
		}  
		System.out.println("上传成功名字是："+newFileName);
		return newFileName;
	}
	
	@RequestMapping(value="uploadvideo",method=RequestMethod.POST)
	@ResponseBody
	public String uploadVideo(HttpServletRequest request){
		Map<String,Object> data = new HashMap<String,Object>();
		String newFileName= "default.mp4";
		try {
			 newFileName=new SimpleDateFormat("yyyyMMddhhmmssSSS").format(new Date())+".mp4";
			 File file = new File(newFileName);
			 MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
			 MultipartFile mfile = multipartRequest.getFile("file");
			 CommonsMultipartFile cf= (CommonsMultipartFile)mfile;
			 DiskFileItem fi = (DiskFileItem)cf.getFileItem();
			 file = fi.getStoreLocation(); 
			 data.put("videoName", newFileName);
			 try {
				 if(UploadUtils.uploadImg(UploadSetting.getQhdvideoServerUrl(),file, newFileName)){
					
				 }
			} catch (Exception e) {
			}
			
		} catch (Exception e) {
		}  
		System.out.println("上传成功名字是："+newFileName);
		return newFileName;
	}
	
	/**
     * 文件下载
     * @Description: 
     * @param fileName
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/download")
    public String downloadFile(@RequestParam("fileName") String fileName,
            HttpServletRequest request, HttpServletResponse response) {
        if (fileName != null) {
            String realPath = request.getSession().getServletContext().getRealPath(
                    "upload/");
            File file = new File(realPath, fileName);
            if (file.exists()) {
                response.setContentType("application/force-download");// 设置强制下载不打开
                response.addHeader("Content-Disposition",
                        "attachment;fileName=" + fileName);// 设置文件名
                byte[] buffer = new byte[1024];
                FileInputStream fis = null;
                BufferedInputStream bis = null;
                try {
                    fis = new FileInputStream(file);
                    bis = new BufferedInputStream(fis);
                    OutputStream os = response.getOutputStream();
                    int i = bis.read(buffer);
                    while (i != -1) {
                        os.write(buffer, 0, i);
                        i = bis.read(buffer);
                    }
                } catch (Exception e) {
                    // TODO: handle exception
                    e.printStackTrace();
                } finally {
                    if (bis != null) {
                        try {
                            bis.close();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    if (fis != null) {
                        try {
                            fis.close();
                        } catch (IOException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return null;
    }
}
