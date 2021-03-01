package com.modoodesigner.web.payload;

import com.modoodesigner.domain.application.commands.PendantRegisterCommand;
import com.modoodesigner.domain.model.part.common.PlatingColor;
import com.modoodesigner.domain.model.part.pendant.MountingType;
import com.modoodesigner.domain.model.part.common.Material;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PendantRegistrationPayload {

    private String name;
    private String code;
    private int buyPrice;
    private int stock;
    private String color;
    private String material;
    private String mountingType;

    public PendantRegisterCommand toCommand() {
        PendantRegisterCommand command = PendantRegisterCommand.builder()
                .name(name)
                .buyPrice(buyPrice)
                .stock(stock)
                .color(PlatingColor.findByTypeName(color))
                .material(Material.findByTypeName(material))
                .mountingType(MountingType.findByTypeName(mountingType))
                .build();
        return command;
    }
}
