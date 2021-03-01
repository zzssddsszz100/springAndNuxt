package com.modoodesigner.domain.application.impl;

import com.modoodesigner.domain.application.commands.PendantRegisterCommand;
import com.modoodesigner.domain.model.part.common.PartRegistrationException;
import com.modoodesigner.domain.model.part.pendant.Pendant;
import com.modoodesigner.domain.model.part.pendant.PendantRegistrationManagement;
import com.modoodesigner.domain.model.part.pendant.PendantRepository;
import com.modoodesigner.factory.RegisterCommandFactory;
import com.modoodesigner.domain.model.part.common.PartExistsException;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
@ExtendWith(MockitoExtension.class)
class PendantServiceImplTest {
    @Mock
    private PendantRegistrationManagement pendantRegistrationManagementMock;
    @Mock
    static PendantRepository pendantRepositoryMock;
    @InjectMocks
    static PendantServiceImpl instance;

    @SneakyThrows
    @Test
    void register_existName_shouldFail() {
        PendantRegisterCommand command = RegisterCommandFactory.getPendantCommand();
        Pendant pendant = new Pendant(command);
        doThrow(PartExistsException.class).when(pendantRegistrationManagementMock)
                .register(command);
        Assertions.assertThrows(PartRegistrationException.class, () -> instance.register(command));
    }
}
