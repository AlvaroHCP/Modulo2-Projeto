package com.devinhouse.DEVinPharmacy.connection;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class MyHttpResponse {
    private static Object body;
    public static ResponseEntity<HttpStatus> ok(){
        return ResponseEntity.ok().build();
    };
    public static ResponseEntity<?> ok(Object body){
        return ResponseEntity.ok(body);
    };
}
