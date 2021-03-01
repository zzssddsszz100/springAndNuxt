package com.modoodesigner.web.apis;

import com.modoodesigner.domain.application.UserService;
import com.modoodesigner.domain.application.commands.UserRegisterCommand;
import com.modoodesigner.domain.model.user.EmailAddressExistsException;
import com.modoodesigner.domain.model.user.UserRegistrationException;
import com.modoodesigner.domain.model.user.UsernameExistsException;
import com.modoodesigner.web.payload.RegistrationPayload;
import com.modoodesigner.web.results.ApiResult;
import com.modoodesigner.web.results.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequiredArgsConstructor
@Slf4j
public class RegistrationApiController extends AbstractBaseController{
    private final UserService service;

    @PostMapping("/api/registrations")
    public ResponseEntity<ApiResult> register(@Valid @RequestBody RegistrationPayload payload, HttpServletRequest request) {
        try {
            UserRegisterCommand command = payload.toCommand();
            addTriggeredBy(command, request);
            service.register(command);
            return Result.created();
        } catch (UserRegistrationException e) {
            String errorMessage = "회원가입에 실패하였습니다.";
            if(e instanceof UsernameExistsException){
                errorMessage = "유저이름이 이미 등록되어 있습니다.";
            } else if (e instanceof EmailAddressExistsException) {
                errorMessage = "이메일이 이미 등록되어 있습니다.";
            }
            return Result.failure(errorMessage);
        }
    }
}
