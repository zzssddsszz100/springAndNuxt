package com.modoodesigner.domain.model.user.events;

import com.modoodesigner.domain.common.event.DomainEvent;
import com.modoodesigner.domain.common.event.TriggeredFrom;
import com.modoodesigner.domain.model.user.User;

public class UserRegisteredEvent extends DomainEvent {
    public UserRegisteredEvent(User user, TriggeredFrom triggeredFrom) {
        super(user.getUserId(), triggeredFrom);
    }

    @Override
    public String toString() {
        return "UserRegisteredEvent{userId=" + getUserId() + '}';
    }

}
