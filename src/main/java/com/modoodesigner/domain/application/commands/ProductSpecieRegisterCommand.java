package com.modoodesigner.domain.application.commands;

import com.modoodesigner.domain.model.part.chain.SuppliedChain;
import com.modoodesigner.domain.model.part.pendant.SuppliedPendant;

import java.util.ArrayList;
import java.util.List;

public class ProductSpecieRegisterCommand {
    List<SuppliedChain> chainList = new ArrayList<>();
    List<SuppliedPendant> pendantList = new ArrayList<>();
}
