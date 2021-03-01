package com.modoodesigner.infrastructure.messaging;

import com.modoodesigner.domain.common.event.DomainEvent;
import com.modoodesigner.domain.common.event.DomainEventPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Qualifier;

//@Component
@RequiredArgsConstructor
@Slf4j
public class AmqpDomainEventPublisher implements DomainEventPublisher {

    private final RabbitTemplate rabbitTemplate;
    @Qualifier("domainEventsExchange")
    private final FanoutExchange exchange;

    @Override
    public void publish(DomainEvent event) {
        log.debug("도메인 이벤트: " + event);
        try {
            rabbitTemplate.convertAndSend(exchange.getName(), "", event);
        } catch (AmqpException e) {
            log.error("MQ로 도메인 이벤트 전송 실패", e);
        }
    }
}
