package com.modoodesigner.domain.model.part.common;

import com.modoodesigner.domain.common.model.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
/*

public abstract class BasePart extends BaseEntity {

    public BasePart(PartRegisterCommand command) {
        buyPrice = command.getBuyPrice();
        name = command.getName();
        stock = command.getStock();
        color = command.getColor();
        material = command.getMaterial();
    }

    private String name;

    @Column(unique = true)
    private String code;

    private int buyPrice;

    private int sellPrice;

    private int stock;

    @Enumerated(EnumType.STRING)
    private PlatingColor color;

    @Enumerated(EnumType.STRING)
    private Material material;


    public void setCode(String code) {
        this.code = code;
    }
}
*/
