package com.ysusoft.upload.util;

import java.io.File;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;

/**
 * 上传文件工具类
 * 
 * @author chengql
 *
 */
public class UploadUtils {

	public static boolean uploadImg(String targetURL, File targetFile,String newFilename) {

		PostMethod filePost = new PostMethod(targetURL);

		try {
			Part[] parts ={new FilePart("file", targetFile),new StringPart("fileName", newFilename) };
			filePost.setRequestEntity(new MultipartRequestEntity(parts, filePost.getParams()));
			HttpClient client = new HttpClient();
			client.getHttpConnectionManager().getParams().setConnectionTimeout(5000);
			int status = client.executeMethod(filePost);
			if (status == HttpStatus.SC_OK) {
				return true;
			} else {
				return false;
			}
		} catch (Exception ex) {
			System.out.println("上传异常");
			ex.printStackTrace();
			return false;
		} 
	}
}
