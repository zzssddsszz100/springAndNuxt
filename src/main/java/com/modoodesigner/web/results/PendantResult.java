package com.modoodesigner.web.results;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.ResponseEntity;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class PendantResult {

    public static ResponseEntity<ApiResult> created(){
        return Result.created();
    }
}
