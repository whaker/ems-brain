package com.identity.ems;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@EnableScheduling
@SpringBootApplication
@EnableAsync
@ComponentScan(basePackages = "com.identity.ems")
public class BemsBrainApplication {
	private static final Logger logger = LoggerFactory.getLogger(BemsBrainApplication.class);

	@Resource
	int limitRangeMinValue;

	@Resource
	int limitRangeMaxValue;

	@PostConstruct
	public void started() {
		logger.info("Point value limit range min : {}", limitRangeMinValue);
		logger.info("Point value limit range max : {}", limitRangeMaxValue);
		logger.info("BEMS Analysis Process Started");
	}

	@Bean
	public TaskExecutor taskExecutor() {
		ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
		taskExecutor.setCorePoolSize(10);
		taskExecutor.setMaxPoolSize(200);
		taskExecutor.setQueueCapacity(150);
		return taskExecutor;
	}

	public static void main(String[] args) {
		SpringApplication.run(BemsBrainApplication.class, args);
	}
}
