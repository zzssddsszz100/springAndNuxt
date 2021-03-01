package com.modoodesigner.domain.application.commands;

import com.modoodesigner.domain.model.part.common.Material;
import com.modoodesigner.domain.model.part.common.PlatingColor;
import com.modoodesigner.domain.model.part.pendant.MountingType;
import com.modoodesigner.domain.model.user.UserId;
import com.modoodesigner.utils.IpAddress;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Builder
@Getter
public class PendantRegisterCommand extends UserCommand {
    private String name;
    private int buyPrice;
    private int stock;
    private String code;
    private PlatingColor color;
    private Material material;
    private MountingType mountingType;

    @Override
    public UserId getUserId() {
        return null;
    }

    @Override
    public IpAddress getIpAddress() {
        return null;
    }
}
