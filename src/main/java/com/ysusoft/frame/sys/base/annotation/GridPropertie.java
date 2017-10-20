package com.ysusoft.frame.sys.base.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * @author qlcheng
 * @date 2017年4月12日 下午4:59:29
 * Bean属性注解，用于代码生成
 */
@Target(value={ElementType.TYPE,ElementType.FIELD})
@Retention(value=RetentionPolicy.RUNTIME)
public @interface GridPropertie {

	//列名
	String columnName() default "";
	
	//是否显示
	boolean isShow() default true;
	
	//是否主键
	boolean isKey() default false;
	
	//描述
	String summary() default "";
}
