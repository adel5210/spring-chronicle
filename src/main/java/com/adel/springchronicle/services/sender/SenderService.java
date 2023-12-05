package com.adel.springchronicle.services.sender;

import com.adel.springchronicle.config.QueueConfig;
import com.adel.springchronicle.event.QueueEvent;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.openhft.chronicle.queue.ExcerptAppender;
import net.openhft.chronicle.queue.impl.single.SingleChronicleQueue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.time.Clock;

@Service
@Slf4j
@RequiredArgsConstructor
@DependsOn("queueConfig")
public class SenderService {

	private final ApplicationEventPublisher applicationEventPublisher;
	private final QueueConfig queueConfig;

	@PostConstruct
	public void init(){
		final ExcerptAppender appender = queueConfig.getQueue().acquireAppender();
		for (int i = 0; i <3; i++) {
			appender.writeText("Test"+i);
		}
		applicationEventPublisher.publishEvent(new QueueEvent(this, Clock.systemUTC()));
	}



}
