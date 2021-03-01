package com.modoodesigner.domain.model.tag;

import com.modoodesigner.domain.common.model.BaseEntity;
import com.modoodesigner.domain.model.product.ProductSpecie;

import javax.persistence.*;

@Entity
@Table(name = "TAG")
public class Tag extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TAG_ID")
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_SPECIE_ID")
    private ProductSpecie productSpecie;
}
