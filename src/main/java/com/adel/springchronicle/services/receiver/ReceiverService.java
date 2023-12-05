package com.adel.springchronicle.services.receiver;

import com.adel.springchronicle.config.QueueConfig;
import com.adel.springchronicle.event.QueueEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.openhft.chronicle.queue.ExcerptTailer;
import net.openhft.chronicle.queue.impl.single.SingleChronicleQueue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@DependsOn("queueConfig")
@RequiredArgsConstructor
public class ReceiverService implements ApplicationListener<QueueEvent> {

	private final QueueConfig queueConfig;

	@Override
	public void onApplicationEvent(final QueueEvent event) {
		log.info("Timestamp: {}", event.getTimestamp());
		readQueue();
	}

	public void readQueue() {
		final SingleChronicleQueue queue = queueConfig.getQueue();
		try (final ExcerptTailer tailer = queue.createTailer()) {
			String msg;
			do {
				msg = tailer.readText();
				Optional.ofNullable(msg)
								.ifPresent(p -> log.info("Receiving -> {}", p));
			} while (null != msg);
		}
	}
}
