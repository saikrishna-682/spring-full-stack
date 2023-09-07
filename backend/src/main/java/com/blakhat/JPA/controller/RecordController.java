package com.blakhat.JPA.controller;

import com.blakhat.JPA.jwt.JWTUtil;
import com.blakhat.JPA.model.Record;
import com.blakhat.JPA.model.RecordRegistrationRequest;
import com.blakhat.JPA.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping()
public class RecordController {
    @Autowired
    private RecordService recordService;

    private final JWTUtil jwtUtil;

    public RecordController(RecordService recordService, JWTUtil jwtUtil) {
        this.recordService = recordService;
        this.jwtUtil = jwtUtil;
    }


    @GetMapping("/api/v1/records")
    public List<Record> getRecords(){
        return recordService.getRecords();
    }

    @GetMapping("/api/v1/records/{id}")
    public Optional<Record> getRecordId(@PathVariable("id") Long id){
        return recordService.getRecordId(id);
    }


    // adding security
    @PostMapping("/api/v1/records")
    public ResponseEntity<?> postRecord(@RequestBody Record record){

         recordService.postRecord(record);
        String jwtToken = jwtUtil.issueToken(record.getMail(), "ROLE_USER");
        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, jwtToken)
                .build();

    }


    @DeleteMapping("/api/v1/records/{id}")
    public void deleteRecord(@PathVariable("id") Long id){
         recordService.deleteRecord(id);
    }

    @PutMapping("/api/v1/records/{id}")
    public Record putRecord(@RequestBody Record record, @PathVariable("id") Long id){
        return recordService.putRecord(id, record);


    }



}
