package com.devinhouse.DEVinPharmacy.connection;

import com.devinhouse.DEVinPharmacy.model.Estoque;
import com.devinhouse.DEVinPharmacy.model.Farmacia;
import com.devinhouse.DEVinPharmacy.model.Medicamento;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class MyHttpResponse {

    public static ResponseEntity<HttpStatus> ok(){
        return ResponseEntity.ok().build();
    };
    public static ResponseEntity<HttpStatus> badRequest(){
        return ResponseEntity.badRequest().build();
    };
    public static ResponseEntity<?> badRequestBody(Object message) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    };
    public static ResponseEntity<HttpStatus> noContent(){
        return ResponseEntity.noContent().build();
    };
    public static ResponseEntity<?> noContentBody(Object message) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(message);
    };
    public static ResponseEntity<HttpStatus> notFound(){
        return ResponseEntity.notFound().build();
    };
    public static ResponseEntity<?> notFoundBody(Object message) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
    };

    public static ResponseEntity<Object> Ok(Object body){
        return ResponseEntity.ok(body);
    };


    public static ResponseEntity<Farmacia> farmaciaOk(Farmacia body){
        return ResponseEntity.ok(body);
    };
    public static ResponseEntity<Medicamento> medicamentoOk(Medicamento body){
        return ResponseEntity.ok(body);
    };
    public static ResponseEntity<Estoque> estoqueOk(Estoque body){
        return ResponseEntity.ok(body);
    };
    public static ResponseEntity<List<Farmacia>> farmaciasOk(List<Farmacia> body){
        return ResponseEntity.ok(body);
    };
    public static ResponseEntity<List<Medicamento>> medicamentosOk(List<Medicamento> body){ return ResponseEntity.ok(body); };
    public static ResponseEntity<List<Estoque>> estoquesOk(List<Estoque> body){
        return ResponseEntity.ok(body);
    };
}
