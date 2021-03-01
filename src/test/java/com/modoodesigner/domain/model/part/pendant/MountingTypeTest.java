package com.modoodesigner.domain.model.part.pendant;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class MountingTypeTest {
    @Test
    void enumTest(){
//        MountingType.valueOf("무도금");
//        MountingType a;
        MountingType a = MountingType.findByTypeName("1고리");
        log.info(a.typeCode);

    }

}