package com.modoodesigner.domain.common.event;

public interface DomainEventPublisher {

    void publish(DomainEvent event);
}
