package com.modoodesigner.domain.application.impl;

import com.modoodesigner.domain.application.PendantService;
import com.modoodesigner.domain.application.commands.PendantRegisterCommand;
import com.modoodesigner.domain.model.part.pendant.PendantRegistrationManagement;
import com.modoodesigner.domain.model.part.pendant.PendantRepository;
import com.modoodesigner.domain.model.part.common.PartRegistrationException;
import com.modoodesigner.domain.model.part.pendant.Pendant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PendantServiceImpl implements PendantService {
    private final PendantRegistrationManagement pendantRegistrationManagement;
    private final PendantRepository repository;

    public Pendant findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public Pendant findByName(String name) {
        return repository.findByName(name).orElseThrow();
    }

    public Page<Pendant> findByAll(Pageable pageable){return repository.findAll(pageable);}

    @Override
    public void register(PendantRegisterCommand command) throws PartRegistrationException {
        Pendant part = pendantRegistrationManagement.register(command);
    }

}
