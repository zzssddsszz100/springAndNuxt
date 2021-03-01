package com.modoodesigner.domain.model.part.pendant;

import com.modoodesigner.domain.application.commands.PendantRegisterCommand;
import com.modoodesigner.domain.model.part.common.PartExistsException;
import com.modoodesigner.domain.model.part.common.PartRegistrationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PendantRegistrationManagement {
    private final PendantRepository repository;

    public Pendant register(PendantRegisterCommand command) throws PartRegistrationException {
        Pendant pendant = new Pendant(command);
        if (repository.existsByCode(pendant.getCode())) {
            throw new PartExistsException();
        }

        return repository.save(pendant);


    }


}
