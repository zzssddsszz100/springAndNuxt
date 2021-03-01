package com.modoodesigner.factory;

import com.modoodesigner.domain.application.commands.PendantRegisterCommand;
import com.modoodesigner.domain.model.part.common.PlatingColor;
import com.modoodesigner.domain.application.commands.ChainRegisterCommand;
import com.modoodesigner.domain.model.part.common.Material;
import com.modoodesigner.domain.model.part.pendant.MountingType;

public final class RegisterCommandFactory {
    public static PendantRegisterCommand getPendantCommand(){
        PendantRegisterCommand command = PendantRegisterCommand.builder()
                .name("팬던트이름")
                .buyPrice(1000)
                .material(Material.SILVER)
                .color(PlatingColor.BLACK)
                .mountingType(MountingType.SINGLE)
                .code("code")
                .stock(20)
                .build();
        return command;
    }

    public static ChainRegisterCommand getChainCommand() {
        ChainRegisterCommand command = ChainRegisterCommand.builder()
                .name("체인이름")
                .buyPrice(1000)
                .material(Material.SILVER)
                .color(PlatingColor.NONE)
                .stock(200)
                .build();
        return command;


    }

}
