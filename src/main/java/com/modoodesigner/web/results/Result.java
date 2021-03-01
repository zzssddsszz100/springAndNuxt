package com.modoodesigner.web.results;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Result {
    public static ResponseEntity<ApiResult> created(){
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    public static ResponseEntity<ApiResult> ok(){
        return ResponseEntity.ok().build();
    }

    public static ResponseEntity<ApiResult> ok(String message){
        return ok(ApiResult.message(message));
    }

    public static ResponseEntity<ApiResult> ok(ApiResult payload) {
        return ResponseEntity.ok(payload);
    }

    public static ResponseEntity<ApiResult> failure(String message){
        return ResponseEntity.badRequest().body(ApiResult.message(message));
    }

    public static ResponseEntity<ApiResult> serverError(String message, String errorReferenceCode){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ApiResult.error(message, errorReferenceCode));
    }

    public static ResponseEntity<ApiResult> notFond() {
        return ResponseEntity.notFound().build();
    }

    public static ResponseEntity<ApiResult> unauthenticated(){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
