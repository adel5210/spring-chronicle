package com.adel.springchronicle.services.sender;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.openhft.chronicle.core.time.UniqueMicroTimeProvider;
import net.openhft.chronicle.queue.RollCycle;
import net.openhft.chronicle.queue.RollCycles;
import net.openhft.chronicle.queue.impl.single.SingleChronicleQueueBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

@Service
@RequiredArgsConstructor
@Slf4j
public class SenderService {

	@Value("${queue.base.path}")
	public String queueBasePath;

	@PostConstruct
	public void init(){
			final SingleChronicleQueueBuilder queueBuilder = SingleChronicleQueueBuilder
							.single(queueBasePath)
							.rollCycle(RollCycles.DEFAULT)
							.timeProvider(new UniqueMicroTimeProvider());
	}



}
