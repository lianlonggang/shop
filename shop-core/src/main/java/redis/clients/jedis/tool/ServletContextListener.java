package redis.clients.jedis.tool;

import javax.servlet.ServletContextEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.tool.JedisTool;

public class ServletContextListener implements javax.servlet.ServletContextListener {
	private static Logger log = LoggerFactory.getLogger(ServletContextListener.class);
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		log.info("ServletContextListener start.....");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		log.info("ServletContextListener Destroyed.....");
		if(JedisTool.getShardedJedisPool()!=null){
			log.error("close redis pool.....");
			JedisTool.getShardedJedisPool().destroy();
		}
	}

}
