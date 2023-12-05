package com.adel.springchronicle.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

import java.util.concurrent.Executors;

@Configuration
@Slf4j
public class EventConfig {

	@Bean
	public ApplicationEventMulticaster applicationEventMulticaster(){
		final SimpleApplicationEventMulticaster eventMulticaster = new SimpleApplicationEventMulticaster();
		final SimpleAsyncTaskExecutor simpleAsyncTaskExecutor = new SimpleAsyncTaskExecutor("event-thread-");
//		simpleAsyncTaskExecutor.setVirtualThreads(true); //only on jdk 21
		eventMulticaster.setTaskExecutor(simpleAsyncTaskExecutor);
		return eventMulticaster;
	}

}
