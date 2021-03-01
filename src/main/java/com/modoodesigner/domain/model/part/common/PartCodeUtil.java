/*
package com.modoodesigner.domain.model.part.common;

import com.modoodesigner.domain.model.part.chain.Chain;
import com.modoodesigner.domain.model.part.pendant.Pendant;
import com.modoodesigner.domain.model.part.pendant.MountingType;

public class PartCodeUtil {
    public static void generate(BasePart part) {
        StringBuffer code = new StringBuffer();

        String name = part.getName();
        PlatingColor color = part.getColor();
        Material material = part.getMaterial();

        code.append(name);
        code.append("/");

        code.append(color.getTypeName());
        code.append("/");

        if (part instanceof Pendant){
            Pendant pendant = (Pendant) part;
            MountingType mountingType = pendant.getMountingType();
            code.append(mountingType.getTypeName());
            code.append("/");

        }else if (part instanceof Chain){
            Chain chain = (Chain) part;
        }
        code.append(material.getTypeName());
        part.setCode(code.toString());
    }


}
*/
