package com.modoodesigner.web.apis;

import com.modoodesigner.domain.application.ProductService;
import com.modoodesigner.domain.application.commands.ProductRegisterCommand;
import com.modoodesigner.domain.model.product.ProductRegistrationException;
import com.modoodesigner.web.payload.ProductRegistrationPayload;
import com.modoodesigner.web.results.ApiResult;
import com.modoodesigner.web.results.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
@Slf4j
public class ProductRegistrationApiController {
    private final ProductService service;

    @PostMapping("/api/product")
    public ResponseEntity<ApiResult> register(@RequestBody ProductRegistrationPayload payload, HttpServletRequest request){
        try {
            ProductRegisterCommand command = payload.toCommand();
            service.register(command);
            return Result.created();
        }catch (ProductRegistrationException e){
            String errorMessage = "상품등록에 실패하였습니다.";
            return Result.failure(errorMessage);
        }
    }
}
