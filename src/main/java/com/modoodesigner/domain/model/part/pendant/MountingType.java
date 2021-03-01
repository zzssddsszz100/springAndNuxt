package com.modoodesigner.domain.model.part.pendant;

import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

@Getter
public enum MountingType {
    SINGLE("SINGLE","1고리"),DOUBLE("DOUBLE","2고리"),SINGLE_HOLE("SINGLE_HOLE","1오링"),
    DOUBLE_HOLE("DOUBLE_HOLE","2오링"),PASSING("PASSING","통과"),
    EMPTY("NONE","없음");

    String typeCode;
    String typeName;

    MountingType(String typeCode,String typeName) {
        this.typeCode = typeCode;
        this.typeName = typeName;
    }

    public static MountingType findByTypeName(String name){
        return Arrays.stream(MountingType.values())
                .filter(mountingType -> mountingType.hasMountTypeName(name))
                .findAny()
                .orElse(EMPTY);
    }

    private boolean hasMountTypeName(String name) {
        return Objects.equals(name,typeName);
    }

    public String getTypeCode(){return typeCode;}

    public String getTypeName(){return typeName;}

}
