package com.ysusoft.frame.sys.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Map;

import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import freemarker.template.Template;

/**
 * 模板工具类
 * @author chengql
 * @date 2016-9-28
 */
public class FtlUtil implements Runnable{
	private Map<String,Object> datas;
	private String ftlName;
	private FreeMarkerConfigurer fkc;
	private String path;
	
	public FtlUtil(Map<String,Object> datas,String ftlName,FreeMarkerConfigurer fkc,String path){
		this.datas = datas;
		this.ftlName = ftlName;
		this.fkc = fkc;
		this.path = path;
	}
	
	public void run() {
		try{
			Template t = this.fkc.getConfiguration().getTemplate(this.ftlName);
			File afile = new File(this.path);
			if (!afile.getParentFile().exists()) {
				afile.getParentFile().mkdirs();
			}
			if (!afile.exists()) {
				afile.createNewFile();
			}
			Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(afile),"UTF-8"));
			t.process(datas, out);
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
