package com.ysusoft.${modelName}.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.ysusoft.${modelName}.bean.${modelName?cap_first};
import com.ysusoft.${modelName}.service.${modelName?cap_first}Service;
/**
 * @desc ${desc}
 * @date ${date}
 * @author ${uName}
 * @version 1.0
 */
@Controller
@RequestMapping("${modelName}web")
public class ${modelName?cap_first}Controller{

    @Autowired
    private ${modelName?cap_first}Service ${modelName}Service;
    
    /**
     * 索引
     */
    @RequestMapping("index")
    public ModelAndView index(){
      return new ModelAndView("${modelName}/${modelName}");
    }
    
   
}