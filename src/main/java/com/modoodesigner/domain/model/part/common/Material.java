package com.modoodesigner.domain.model.part.common;

import com.modoodesigner.domain.model.part.pendant.MountingType;
import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

@Getter
public enum Material {
    SILVER("SILVER","실버"),GOLD24K("GOLD24K","순금"),GOLD14K("GOLD14K","14K골드"),GOLD18K("GOLD18K","18K골드"),
    EMPTY("NONE","없음");

    String typeCode;
    String typeName;

    Material(String typeCode,String typeName) {
        this.typeCode = typeCode;
        this.typeName = typeName;
    }

    public static Material findByTypeName(String name){
        return Arrays.stream(Material.values())
                .filter(material -> material.hasMaterialName(name))
                .findAny()
                .orElse(EMPTY);
    }

    private boolean hasMaterialName(String name) {
        return Objects.equals(name,typeName);
    }

    public String getTypeCode(){return typeCode;}

    public String getTypeName(){return typeName;}
}
