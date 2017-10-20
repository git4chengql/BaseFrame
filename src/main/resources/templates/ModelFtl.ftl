package com.ysusoft.${modelName}.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;
/**
 * @date ${date}
 * @author ${uName}
 * @version 1.0
 */
 @Entity
@Table(name="${tableName}")
public class ${modelName?cap_first}{
    @Id
	@GeneratedValue
	 <#list fields as field>
	   <#if field.comments??>
	   //${field.comments}
	   </#if>
	   private ${field.fieldType} ${field.fieldName};
	 </#list>
	 
	   //getter & setter
	 <#list fields as field>
	   public void set${field.fieldName?cap_first}(${field.fieldType} ${field.fieldName}){
	      this.${field.fieldName} = ${field.fieldName};
	   }
	   
	   public ${field.fieldType} get${field.fieldName?cap_first}(){
	      return this.${field.fieldName};
	   }
	 </#list>
}