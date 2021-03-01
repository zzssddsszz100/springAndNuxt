package com.modoodesigner.domain.application.commands;

import com.modoodesigner.domain.common.event.TriggeredFrom;
import com.modoodesigner.utils.IpAddress;
import lombok.EqualsAndHashCode;
import lombok.Getter;


@Getter
@EqualsAndHashCode
public class AnonymousCommand implements TriggeredFrom {

    private IpAddress ipAddress;

    public void triggeredBy(IpAddress ipAddress) {
        this.ipAddress = ipAddress;
    }
    @Override
    public IpAddress getIpAddress() {
        return ipAddress;
    }
}
