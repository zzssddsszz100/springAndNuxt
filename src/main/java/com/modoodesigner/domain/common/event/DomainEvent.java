package com.modoodesigner.domain.common.event;

import com.modoodesigner.domain.model.user.UserId;
import com.modoodesigner.utils.IpAddress;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;

@Getter
public abstract class DomainEvent implements Serializable {

    private static final long serialVersionUID = 6613511380143286986L;

    private final UserId userId;
    private final IpAddress ipAddress;
    private final Date occurredAt;

    public DomainEvent(TriggeredBy triggeredBy) {
        this.userId = triggeredBy.getUserId();
        this.ipAddress = triggeredBy.getIpAddress();
        this.occurredAt = new Date();
    }

    public DomainEvent(UserId userId, TriggeredFrom triggeredFrom){
        this.userId = userId;
        this.ipAddress = triggeredFrom.getIpAddress();
        this.occurredAt = new Date();
    }

}
