package com.blakhat.JPA.controller;

import com.blakhat.JPA.model.Record;
import com.blakhat.JPA.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
public class RecordController {
    @Autowired
    private RecordService recordService;




    @GetMapping("/api/v1/records")
    public List<Record> getRecords(){
        return recordService.getRecords();
    }

    @GetMapping("/api/v1/records/{id}")
    public Optional<Record> getRecordId(@PathVariable("id") Long id){
        return recordService.getRecordId(id);
    }

    @PostMapping("/api/v1/records")
    public Record postRecord(@RequestBody Record record){

        return recordService.postRecord(record);
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
