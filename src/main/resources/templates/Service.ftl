package com.ysusoft.${modelName}.service;


import com.ysusoft.${modelName}.bean.${modelName?cap_first};
import com.ysusoft.${modelName}.dao.${modelName?cap_first}Dao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ysusoft.frame.sys.base.bean.ResultInfo;

/**
 * @desc ${desc}
 * @date ${date}
 * @author ${uName}
 * @version 1.0
 */
@Service
public class ${modelName?cap_first}Service{

	@Autowired
	private ${modelName?cap_first}Dao ${modelName}Dao;
	
	private ResultInfo<${modelName?cap_first}> rs = new ResultInfo<${modelName?cap_first}>();
	
	
	
	 /**
     * 保存
     */
	public ResultInfo<${modelName?cap_first}> add${modelName?cap_first}(${modelName?cap_first} ${modelName}){
		${modelName?cap_first} r = ${modelName}Dao.save(${modelName});
		if(r!=null){
			rs.setSuccess(true);
			rs.setMessage("保存成功");
		}
		return rs;
	}
	
}