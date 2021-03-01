package com.modoodesigner.domain.model.part.chain;

import com.modoodesigner.domain.model.product.ProductSpecie;

import javax.persistence.*;

@Entity
public class SuppliedChain {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SUPPLIED_CHAIN_ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_SPECIE_ID")
    private ProductSpecie productSpecie;

    @ManyToOne
    @JoinColumn(name = "CHAIN_ID")
    private Chain chain;

    private String name;

    private int amount;
}
