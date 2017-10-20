package com.ysusoft.frame.sys.schedule;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 定时任务
 * @author qlcheng
 * @date 2017年4月7日 下午2:16:26
 */
@Configuration
//@EnableScheduling
public class AutoTaskConfig {
		
	@Scheduled(cron = "0/20 * * * * ?") // 每20秒执行一次
		public void scheduler() {
			System.out.println(">>>>>>>>> SchedulingConfig.scheduler()");
		}
}
