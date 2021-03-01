package com.modoodesigner.domain.application.commands;

import com.modoodesigner.domain.model.part.common.Material;
import com.modoodesigner.domain.model.part.common.PlatingColor;
import com.modoodesigner.domain.model.user.UserId;
import com.modoodesigner.utils.IpAddress;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Builder
public class ChainRegisterCommand extends UserCommand {
    private String name;
    private int buyPrice;
    private Material material;
    private PlatingColor color;
    private int stock;

    @Override
    public UserId getUserId() {
        return null;
    }

    @Override
    public IpAddress getIpAddress() {
        return null;
    }
}
