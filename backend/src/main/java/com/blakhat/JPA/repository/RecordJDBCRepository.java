package com.blakhat.JPA.repository;

import com.blakhat.JPA.model.Record;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
@EnableJdbcRepositories
public interface RecordJDBCRepository extends CrudRepository<Record, Long> {



}
