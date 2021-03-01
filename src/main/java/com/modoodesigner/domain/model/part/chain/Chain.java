package com.modoodesigner.domain.model.part.chain;

import com.modoodesigner.domain.application.commands.ChainRegisterCommand;
import com.modoodesigner.domain.common.model.BaseEntity;
import com.modoodesigner.domain.model.part.common.Material;
import com.modoodesigner.domain.model.part.common.PlatingColor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Chain extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CHAIN_ID")
    private Long id;

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

    @OneToMany(mappedBy = "chain")
    private List<SuppliedChain> suppliedChain = new ArrayList<>();

    public Chain(ChainRegisterCommand command) {
    }

//    private void setAutoCode(BasePart basePart){
//        PartCodeUtil.generate(basePart);
//    }
}
