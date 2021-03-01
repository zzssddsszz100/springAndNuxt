package com.modoodesigner.domain.application.impl;

import com.modoodesigner.domain.application.ProductService;
import com.modoodesigner.domain.application.commands.ProductRegisterCommand;
import com.modoodesigner.domain.model.product.ProductRegistrationException;
import com.modoodesigner.domain.model.product.ProductSpecie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    @Override
    public ProductSpecie findById(Long id) {
        return null;
    }

    @Override
    public ProductSpecie findByName(String name) {
        return null;
    }

    @Override
    public void register(ProductRegisterCommand command) throws ProductRegistrationException {

    }
}
