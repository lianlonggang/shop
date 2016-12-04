package com.shop.base.scheduled;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.shop.base.entity.JokeImgLastIdModel;
import com.shop.base.service.JokeImgLastIdService;

@Controller
public class TaskScheduled {
	private static final Logger logger = LoggerFactory.getLogger(TaskScheduled.class);
	
	@Reference
	private JokeImgLastIdService jokeImgLastIdService;
	
	
	@Scheduled(cron = "0 0 5 * * ?")  
	//"0 0/1 15,* * * ?"每一分钟执行一次
	//"0 0 5 * * ?"每日5点执行一次
    //需要注意@Scheduled这个注解，它可配置多个属性：cron\fixedDelay\fixedRate  
    public void test() {  
		
		JokeImgLastIdModel lastIdModel = jokeImgLastIdService.selectByPrimaryKey(1);
		int jokeImgId = lastIdModel.getJokeImgId()+40;
		lastIdModel.setJokeImgId(jokeImgId);
		jokeImgLastIdService.updateByPrimaryKey(lastIdModel);
		logger.info("每日定时任务-----起始查询序列增加40");
    }  
}
