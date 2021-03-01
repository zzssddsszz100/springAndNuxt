package com.modoodesigner.domain.model.product;

import com.modoodesigner.domain.common.model.BaseEntity;
import com.modoodesigner.domain.model.part.chain.SuppliedChain;
import com.modoodesigner.domain.model.part.pendant.SuppliedPendant;
import com.modoodesigner.domain.model.tag.Tag;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class ProductSpecie extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PRODUCT_SPECIE_ID")
    private Long id;

    @OneToMany(mappedBy = "productSpecie")
    private List<SuppliedPendant> suppliedPendants = new ArrayList<>();

    @OneToMany(mappedBy = "productSpecie")
    private List<SuppliedChain> suppliedChains = new ArrayList<>();

    @OneToMany(mappedBy = "productSpecie")
    private List<Tag> tagList = new ArrayList<>();



}
