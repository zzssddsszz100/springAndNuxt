package com.modoodesigner.domain.model.product;

import com.modoodesigner.domain.application.commands.ProductRegisterCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProductRegistrationManagement {
    private final ProductRepository repository;

    public Product register(ProductRegisterCommand command){
        Product product = new Product(command);

        return repository.save(product);
    }
}
