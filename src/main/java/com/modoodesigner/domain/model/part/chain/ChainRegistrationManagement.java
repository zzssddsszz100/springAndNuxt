package com.modoodesigner.domain.model.part.chain;

import com.modoodesigner.domain.application.commands.ChainRegisterCommand;
import com.modoodesigner.domain.model.part.common.PartExistsException;
import com.modoodesigner.domain.model.part.common.PartRegistrationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ChainRegistrationManagement {
    private final ChainRepository repository;

    public Chain register(ChainRegisterCommand command) throws PartRegistrationException{
        Chain chain = new Chain(command);
        if (repository.existsByCode(chain.getCode())) {
            throw new PartExistsException();
        }

        return repository.save(chain);
    }
}
