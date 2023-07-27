package com.blakhat.JPA.service;

import com.blakhat.JPA.model.Record;
import com.blakhat.JPA.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("jdbc")
public class RecordJDBCService {
    @Autowired
    private JdbcTemplate jdbcTemplate;





    private void insertRecord(Record record){
        var sql="INSERT INTO record(name,mail,place,number) VALUES (?,?,?,?)";
        jdbcTemplate.update(sql, record.getName(), record.getMail(), record.getPlace(), record.getNumber());
    }


}
