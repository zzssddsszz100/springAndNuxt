package com.modoodesigner.domain.application.impl;

import com.modoodesigner.domain.application.ChainService;
import com.modoodesigner.domain.application.commands.ChainRegisterCommand;
import com.modoodesigner.domain.model.part.chain.Chain;
import com.modoodesigner.domain.model.part.chain.ChainRegistrationManagement;
import com.modoodesigner.domain.model.part.chain.ChainRepository;
import com.modoodesigner.domain.model.part.common.PartRegistrationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class ChainServiceImpl implements ChainService {
    private final ChainRegistrationManagement chainRegistrationManagement;
    private final ChainRepository repository;

    @Override
    public Chain findById(Long id) {
        return repository.findById(id).orElseThrow();
    }

    @Override
    public Chain findByName(String name) {
        return repository.findByName(name).orElseThrow();
    }

    @Override
    public void register(ChainRegisterCommand command) throws PartRegistrationException {
        Chain chain = chainRegistrationManagement.register(command);
    }
}
