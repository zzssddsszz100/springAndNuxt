package com.modoodesigner.domain.model.part.common;

import lombok.Getter;

import java.util.Arrays;
import java.util.Objects;

@Getter
public enum PlatingColor {
    NONE("NONE","무도금"),PINK("PINK","핑크골드"),WHITE("WHITE","화이트골드"),
    GOLD14K("GOLD14K","14K골드"),BLACK("BLACK","흑도금"),
    ANTIQUE_BRIGHTLY("ANTIQUE_BRIGHTLY","유화유광"),ANTIQUE_SCRATCH("ANTIQUE_SCRATCH","유화무꽝"),
    EMPTY("NONE","없음");

    String typeCode;
    String typeName;

    PlatingColor(String typeCode, String typeName) {
        this.typeCode = typeCode;
        this.typeName = typeName;
    }

    public static PlatingColor findByTypeName(String name){
        return Arrays.stream(PlatingColor.values())
                .filter(platingColor -> platingColor.hasPlatingColorName(name))
                .findAny()
                .orElse(EMPTY);
    }

    private boolean hasPlatingColorName(String name) {
        return Objects.equals(name,typeName);
    }


    public String getTypeCode() {
        return typeCode;
    }

    public String getTypeName() {
        return typeName;
    }


}
