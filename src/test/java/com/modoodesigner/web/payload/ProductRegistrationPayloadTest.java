package com.modoodesigner.web.payload;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
class ProductRegistrationPayloadTest {

    @SneakyThrows
    @Test
    void listJsonToObject(){
        List<String> chain = new ArrayList<>();
        List<String> pendant = new ArrayList<>();

        HashMap<String,HashMap<String,String>> option = new HashMap<String,HashMap<String,String>>();
        HashMap<String,String> detail = new HashMap<String,String>();

        chain.add("1번체인");
        chain.add("2번체인");

        pendant.add("1번팬던트");
        pendant.add("2번펜던트");


        detail.put("1번체인", "2번체인,0.5");

        option.put("1번펜던트",detail);

        ObjectMapper mapper =new ObjectMapper();

        String value = mapper.writeValueAsString(chain);
        log.error(value);


    }

}