package com.ysusoft.frame.sys.base.web;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ysusoft.frame.sys.base.bean.YsuLog;

/**
 * @author qlcheng
 * @date 2017年10月11日 上午10:13:32
 */
public interface LogDao extends JpaRepository<YsuLog, Long> {

}
