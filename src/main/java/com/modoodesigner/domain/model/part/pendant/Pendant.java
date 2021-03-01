package com.modoodesigner.domain.model.part.pendant;

import com.modoodesigner.domain.application.commands.PendantRegisterCommand;
import com.modoodesigner.domain.common.model.BaseEntity;
import com.modoodesigner.domain.model.part.common.Material;
import com.modoodesigner.domain.model.part.common.PlatingColor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Pendant extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PENDANT_ID")
    private Long id;

    private String name;

    @Column(unique = true)
    private String code;

    private int buyPrice;

    private int sellPrice;

    private int stock;

    @ManyToOne
    @JoinColumn(name = "SUPPLIED_PENDANT_ID")
    private SuppliedPendant suppliedPendant;

    @Enumerated(EnumType.STRING)
    private MountingType mountingType;

    @Enumerated(EnumType.STRING)
    private PlatingColor color;

    @Enumerated(EnumType.STRING)
    private Material material;

    public Pendant(PendantRegisterCommand command) {
        buyPrice = command.getBuyPrice();
        name = command.getName();
        stock = command.getStock();
        color = command.getColor();
        code = command.getCode();
        material = command.getMaterial();
        mountingType = command.getMountingType();
    }

//    private void setAutoCode(BasePart basePart){
//        PartCodeUtil.generate(basePart);
//    }

}
