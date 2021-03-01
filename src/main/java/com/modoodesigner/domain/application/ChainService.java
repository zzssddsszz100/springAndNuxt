package com.modoodesigner.domain.application;

import com.modoodesigner.domain.application.commands.ChainRegisterCommand;
import com.modoodesigner.domain.model.part.chain.Chain;
import com.modoodesigner.domain.model.part.common.PartRegistrationException;

public interface ChainService {

    Chain findById(Long id);
    Chain findByName(String name);

    void register(ChainRegisterCommand command) throws PartRegistrationException;
}
