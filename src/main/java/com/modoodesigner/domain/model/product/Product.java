package com.modoodesigner.domain.model.product;

import com.modoodesigner.domain.application.commands.ProductRegisterCommand;
import com.modoodesigner.domain.common.model.BaseEntity;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_ID")
    private Long id;

    private int price;

    private String mainImageAddress;

    private String subImageAddressList;

    private String name;

    private double rateDiscount;

    private int flatDiscount;

    @Lob
    private String detailedHtml;

    public Product(ProductRegisterCommand command) {
        command.getName();
    }

}
