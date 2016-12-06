package com.shop.base.scheduled;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shop.base.convert.Content;
import com.shop.base.service.BaseCodeService;
import com.shop.base.service.JokeLastIdService;

@Controller
public class TaskScheduled {
	private static final Logger logger = LoggerFactory.getLogger(TaskScheduled.class);
	
	@Reference
	private JokeLastIdService jokeLastIdService;
	@Reference
	private BaseCodeService baseCodeService;
	
	
	@Scheduled(cron = "0 0 5 * * ?")  
//	@Scheduled(cron = "0/5 * * * * ?")  
	//"0 0/1 15,* * * ?"每一分钟执行一次
	//"0 0 5 * * ?"每日5点执行一次
    //需要注意@Scheduled这个注解，它可配置多个属性：cron\fixedDelay\fixedRate  
    public void test() {  
		jokeLastIdService.addLastId(Content.JOKE_TYPE_IMG);
		jokeLastIdService.addLastId(Content.JOKE_TYPE_TEXT);
		logger.info("每日定时任务---增加最新ID");
    }  
}
