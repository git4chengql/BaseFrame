package com.ysusoft.frame.sys.autocode.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.ysusoft.frame.sys.autocode.util.PackageUtil;
import com.ysusoft.frame.sys.base.annotation.GridPropertie;
import com.ysusoft.frame.sys.base.bean.BeanMsg;
import com.ysusoft.frame.sys.base.bean.FieldMsg;

/**
 * @author qlcheng
 * @date 2017年4月12日 下午5:10:29 代码自动生成功能
 */
@Service
public class CodeService {

	/**
	 * 获取所有实体类
	 * 
	 * @param packageName
	 *            包名称
	 * @return 实体类集合
	 * @throws ClassNotFoundException
	 */
	public List<String> getAllEntity(String packageName) {
		List<String> classNames = PackageUtil.getClassName(packageName);
		List<String> entities = new ArrayList<String>();
		for (String className : classNames) {
			Class<?> c;
			try {
				c = Class.forName(className);
				entities.add(c.getName() + ":" + c.getAnnotation(GridPropertie.class).summary());
			} catch (ClassNotFoundException e) {

			}
		}
		return entities;
	}

	/**
	 * 获取实体类相关信息
	 * 
	 * @param beanName
	 * @return
	 */
	public BeanMsg getEntityMsg(String beanName) {
		BeanMsg bm = new BeanMsg();
		List<FieldMsg> fields = new ArrayList<FieldMsg>();
		FieldMsg fm;
		try {
			Class<?> bean = Class.forName(beanName);
			String summary = bean.getAnnotation(GridPropertie.class).summary();
			bm.setSummary(summary);
			for (Field field : bean.getDeclaredFields()) {
				fm = new FieldMsg();
				fm.setFieldName(field.getName());
				fm.setFieldSummary(field.getAnnotation(GridPropertie.class).columnName());
				fm.setFieldType(field.getType());
				fields.add(fm);
			}
			bm.setFields(fields);
		} catch (Exception e) {

		}
		return bm;
	}
}
