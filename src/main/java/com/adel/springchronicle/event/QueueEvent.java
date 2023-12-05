package com.adel.springchronicle.event;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

@Getter
@Setter
public class QueueEvent extends ApplicationEvent {
	public QueueEvent(final Object source,
	                  final Clock clock) {
		super(source, clock);
	}
}
