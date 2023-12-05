package com.adel.springchronicle.config;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.openhft.chronicle.core.time.UniqueMicroTimeProvider;
import net.openhft.chronicle.queue.RollCycles;
import net.openhft.chronicle.queue.impl.single.SingleChronicleQueue;
import net.openhft.chronicle.queue.impl.single.SingleChronicleQueueBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class QueueConfig {

	@Value("${queue.base.path}")
	public String queueBasePath;

	@Getter
	private SingleChronicleQueue queue;

	@PostConstruct
	public void chronQueue() {
		log.info("Init chronQueue...");
		final SingleChronicleQueueBuilder queueBuilder = SingleChronicleQueueBuilder
						.single(queueBasePath)
						.rollCycle(RollCycles.DEFAULT)
						.timeProvider(new UniqueMicroTimeProvider());
		this.queue = queueBuilder.build();
//		try(final SingleChronicleQueue queue = queueBuilder.build()){
//			 this.queue = queue;
//		}catch (Exception e) {
//			log.error("Fail to init chronQueue");
//		}
	}

}
