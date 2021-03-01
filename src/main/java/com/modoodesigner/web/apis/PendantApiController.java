package com.modoodesigner.web.apis;

import com.modoodesigner.domain.application.PendantService;
import com.modoodesigner.domain.application.commands.PendantRegisterCommand;
import com.modoodesigner.domain.model.part.common.PartRegistrationException;
import com.modoodesigner.domain.model.part.pendant.Pendant;
import com.modoodesigner.web.results.ApiResult;
import com.modoodesigner.web.results.PendantResult;
import com.modoodesigner.web.results.Result;
import com.modoodesigner.domain.model.part.common.PartExistsException;
import com.modoodesigner.web.payload.PendantRegistrationPayload;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PendantApiController extends AbstractBaseController {
    private final PendantService service;

    @PostMapping("/api/pendants")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity<ApiResult> register(@Valid @RequestBody PendantRegistrationPayload payload, HttpServletRequest request) {
        try {
            PendantRegisterCommand command = payload.toCommand();
            addTriggeredBy(command,request);
            service.register(command);
            return Result.created();
        }catch (PartRegistrationException e) {
            String errorMessage = "등록실패하였습니다.";
          if (e instanceof PartExistsException){
              errorMessage = "팬던트가 이미 등록되어있습니다.";
          }
            return Result.failure(errorMessage);
        }
    }

    @GetMapping("/api/pendants")
    public ResponseEntity<ApiResult> register(Pageable pageable, HttpServletRequest request) {
        Page<Pendant> pendantAll = service.findByAll(pageable);
        return PendantResult.created();
    }


}
