package com.ysusoft.frame.sys.autocode.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.ysusoft.frame.sys.base.annotation.GridPropertie;

/**
 * @author qlcheng
 * @date 2017年4月13日 下午2:54:56
 */
public class PackageUtil {

	public static List<String> getClassName(String packageName) {
		String filePath = ClassLoader.getSystemResource("").getPath() + packageName.replace(".", File.separator);
		List<String> fileNames = getClassName(filePath, null);
		return fileNames;
	}

	/**
	 * 迭代获取所有类
	 * 
	 * @param filePath
	 * @param className
	 * @return
	 */
	private static List<String> getClassName(String filePath, List<String> className) {
		List<String> myClassName = new ArrayList<String>();
		File file = new File(filePath);
		File[] childFiles = file.listFiles();
		for (File childFile : childFiles) {
			if (childFile.isDirectory()) {
				myClassName.addAll(getClassName(childFile.getPath(), myClassName));
			} else {
				String childFilePath = childFile.getPath();
				childFilePath = childFilePath.substring(childFilePath.indexOf(File.separator + "classes") + 9,childFilePath.lastIndexOf("."));
				childFilePath = childFilePath.replace(File.separator, ".");
				try {
					Class<?> cls = Class.forName(childFilePath);
					if (cls.isAnnotationPresent(GridPropertie.class))
						myClassName.add(childFilePath);
				} catch (ClassNotFoundException e) {

				}
			}
		}

		return myClassName;
	}

}
