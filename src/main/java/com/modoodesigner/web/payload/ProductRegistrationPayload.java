package com.modoodesigner.web.payload;

import com.modoodesigner.domain.application.commands.ProductRegisterCommand;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ProductRegistrationPayload {
    private List<String> chain = new ArrayList<>();
    private List<String> pendant = new ArrayList<>();

    public ProductRegisterCommand toCommand() {
        ProductRegisterCommand command = new ProductRegisterCommand();
        return command;
    }
//    private List<String> option = new ArrayList<>();
}
