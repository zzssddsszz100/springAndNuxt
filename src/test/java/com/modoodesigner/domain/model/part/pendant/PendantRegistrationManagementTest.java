package com.modoodesigner.domain.model.part.pendant;

import com.modoodesigner.domain.application.commands.PendantRegisterCommand;
import com.modoodesigner.factory.RegisterCommandFactory;
import com.modoodesigner.domain.model.part.common.PartRegistrationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PendantRegistrationManagementTest {

    @Mock
    PendantRepository repository;
    @InjectMocks
    PendantRegistrationManagement management;

    @Test
    void pendantRegistration_ExistTest_shouldFail() throws PartRegistrationException {
        PendantRegisterCommand command = RegisterCommandFactory.getPendantCommand();
        Pendant pendant = new Pendant(command);
        when(repository.existsByCode(pendant.getCode())).thenReturn(true);
        assertThrows(PartRegistrationException.class, () -> management.register(command));
    }
}