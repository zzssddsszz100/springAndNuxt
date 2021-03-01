package com.modoodesigner.web.apis;

import com.modoodesigner.domain.application.ProductService;
import com.modoodesigner.factory.WithUser;
import com.modoodesigner.web.payload.ProductRegistrationPayload;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@WebMvcTest(controllers = {ProductRegistrationApiController.class})
class ProductRegistrationApiControllerTest {

    @Autowired private MockMvc mvc;

    @MockBean private ProductService service;

    @Test
    @WithUser("admin")
    void register_listOption_shouldListAndReturn201() throws Exception {
        ProductRegistrationPayload payload = new ProductRegistrationPayload();
       /* payload.

        mvc.perform(post("/admin/api/product")
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtils.toJson(c))


                .andExpect(status().is(201));*/
    }

}