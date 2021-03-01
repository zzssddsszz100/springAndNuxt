package com.modoodesigner.web.results;

import org.springframework.http.ResponseEntity;

public class PendantResult {

    public static ResponseEntity<ApiResult> build(){
        ApiResult result = ApiResult.blank();
        return Result.ok(result);
    }
}
