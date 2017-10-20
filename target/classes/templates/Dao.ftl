package com.ysusoft.${modelName}.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.ysusoft.${modelName}.bean.${modelName?cap_first};
/**
 * @desc ${desc}
 * @date ${date}
 * @author ${uName}
 * @version 1.0
 */
public interface ${modelName?cap_first}Dao extends JpaRepository<${modelName?cap_first}, Integer>{


 

 
}