package com.modoodesigner.domain.application.commands;

import com.modoodesigner.domain.model.user.UserId;
import com.modoodesigner.domain.common.event.TriggeredBy;
import com.modoodesigner.utils.IpAddress;
import lombok.*;
import lombok.experimental.SuperBuilder;


public abstract class UserCommand implements TriggeredBy {

    private UserId userId;
    private IpAddress ipAddress;

    public void triggeredBy(UserId userId, IpAddress ipAddress) {
        this.userId = userId;
        this.ipAddress = ipAddress;
    }
}
