package com.ysusoft.frame.sys.base.filter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import com.alibaba.druid.support.http.WebStatFilter;

/**
 * @author qlcheng
 * @date 2017年4月7日 下午2:56:58
 */
@WebFilter(filterName="druidWebStatFilter",urlPatterns="/*",
					initParams={
					         @WebInitParam(name="exclusions",value="*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")// 忽略资源
					      }
)
public class DruidStatFilter extends WebStatFilter {
   
}
