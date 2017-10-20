package com.ysusoft.frame.sys.table.web;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import com.ysusoft.frame.sys.base.bean.Field;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.ServletRequestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import com.ysusoft.frame.sys.util.FtlUtil;
@Controller
@RequestMapping("generate/")
public class TableController {
	@Autowired
	private FreeMarkerConfigurer freeMarkerConfigurer;
	@RequestMapping("index")
	public String index(){
		return "sys/table";
	}
	
	
	/**
	 * 导出模板
	 * @param request
	 * @return
	 */
	@RequestMapping("getTableColumn")
	@ResponseBody
	public boolean generateCode(HttpServletRequest request){
		String model = ServletRequestUtils.getStringParameter(request, "modelName", "");
		String rootText = ServletRequestUtils.getStringParameter(request, "rootText", "");
		String root = rootText.replaceAll("\\\\", "\\" +File.separator).replaceAll("/", "\\" +File.separator);
		String itemsString = request.getParameter("items");
		try{
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
			String dateString = df.format(new Date());// new Date()为获取当前系统时间
			String descString = ServletRequestUtils.getStringParameter(request, "descText", "");
			String tableName = ServletRequestUtils.getStringParameter(request, "tableName", "");
			Map<String,Object> datas = new HashMap<String,Object>();
			char[] cs=model.toCharArray();
		    cs[0]-=32;
		    datas.put("tableName", tableName);
			datas.put("modelName", model);
			datas.put("date", dateString);
			datas.put("uName", System.getProperties().getProperty("user.name"));
			datas.put("desc", descString);
			
			String path = root+File.separator+"src"+File.separator+"main"+File.separator+"java"+File.separator+"com"+
			File.separator+"ysusoft"+File.separator+model+File.separator+"web"+File.separator+String.valueOf(cs)+"Controller.java";
			FtlUtil fu = new FtlUtil(datas, "Controller.ftl", freeMarkerConfigurer, path);
			Thread t1 = new Thread(fu);
			t1.start();
			
			path = root+File.separator+"src"+File.separator+"main"+File.separator+"java"+File.separator+"com"+
			File.separator+"ysusoft"+File.separator+model+File.separator+"dao"+File.separator+String.valueOf(cs)+"Dao.java";
			FtlUtil fu1 = new FtlUtil(datas, "Dao.ftl", freeMarkerConfigurer, path);
			Thread t2 = new Thread(fu1);
			t2.start();
			
			JSONArray rowsJson = JSONArray.fromObject(itemsString);
			List<Field> fields = new ArrayList<Field>();
			for(int i=0;i<rowsJson.size(); i++){
				JSONObject jsonJ = rowsJson.getJSONObject(i);
				String coltypeString = jsonJ.get("data_type").toString();
				if(coltypeString.equals("整型")){
					coltypeString = "Integer";
				}else if(coltypeString.equals("字符型")){
					coltypeString = "String";
				}else if(coltypeString.equals("日期类型")){
					coltypeString = "Date";
				}
				String column_name = jsonJ.get("column_name").toString();
				String column_comment = jsonJ.get("column_comment").toString();
				String data_type = coltypeString;
				Field field = new Field();
				field.setFieldType(data_type);
				field.setFieldName(column_name);
				field.setComments(column_comment);
				fields.add(field);
				datas.put("fields", fields);
			}
			path = root+File.separator+"src"+File.separator+"main"+File.separator+"java"+File.separator+"com"+
			File.separator+"ysusoft"+File.separator+model+File.separator+"bean"+File.separator+String.valueOf(cs)+".java";
			FtlUtil fu3 = new FtlUtil(datas, "ModelFtl.ftl", freeMarkerConfigurer, path);
			Thread t3 = new Thread(fu3);
			t3.start();
			
			path = root+File.separator+"src"+File.separator+"main"+File.separator+"java"+File.separator+"com"+
			File.separator+"ysusoft"+File.separator+model+File.separator+"service"+File.separator+String.valueOf(cs)+"Service.java";
			FtlUtil fu4 = new FtlUtil(datas, "Service.ftl", freeMarkerConfigurer, path);
			Thread t4 = new Thread(fu4);
			t4.start();
			
			//生成ftl
			path = root+File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"templates"+
			File.separator+model+File.separator+model+".ftl";
			FtlUtil fu5 = new FtlUtil(datas, "Ftl.ftl", freeMarkerConfigurer, path);
			Thread t5 = new Thread(fu5);
			t5.start();
			
			//生成JS
			JSONArray itemJson = JSONArray.fromObject(itemsString);
			List<Field> fltem = new ArrayList<Field>();
			for(int i=0;i<itemJson.size(); i++){
				JSONObject jitem = itemJson.getJSONObject(i);
				String colname = jitem.get("column_name").toString();
				String colcomment = jitem.get("column_comment").toString();
				Field ftm = new Field();
				ftm.setFieldName(colname);
				ftm.setComments(colcomment);
				fltem.add(ftm);
				datas.put("fltem", fltem);
			}
			path = root+File.separator+"src"+File.separator+"main"+File.separator+"resources"+File.separator+"static"+File.separator+"js"+File.separator+"ysusoft"+
			File.separator+model+File.separator+model+".js";
			fu5 = new FtlUtil(datas, "Js.ftl", freeMarkerConfigurer, path);
			Thread t6 = new Thread(fu5);
			t6.start();
		}catch(Exception e){
			e.printStackTrace();
		}
		return true;
}
	
}
