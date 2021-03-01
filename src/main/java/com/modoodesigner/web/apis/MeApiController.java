package com.modoodesigner.web.apis;

import com.modoodesigner.domain.application.UserService;
import com.modoodesigner.domain.common.security.CurrentUser;
import com.modoodesigner.domain.model.user.SimpleUser;
import com.modoodesigner.domain.model.user.User;
import com.modoodesigner.web.results.ApiResult;
import com.modoodesigner.web.results.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class MeApiController {
    private final UserService userService;

    @GetMapping("/api/me")
    public ResponseEntity<ApiResult> getMyData(@CurrentUser SimpleUser currentUser){
        User user = userService.findById(currentUser.getUserId());

        Map<String, Object> userData = new HashMap<>();
        userData.put("uid",user.getUserId().value());
        userData.put("name",user.getUsername());
        userData.put("roles",user.getRoles());

        ApiResult apiResult = ApiResult.blank()
                .add("user", userData);
        return Result.ok(apiResult);
    }
}
