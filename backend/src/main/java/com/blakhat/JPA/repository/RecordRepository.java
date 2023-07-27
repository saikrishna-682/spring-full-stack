package com.blakhat.JPA.repository;

import com.blakhat.JPA.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface RecordRepository extends JpaRepository<Record, Long> {


    boolean existsByMail(String mail);

    boolean existsByNumber(Integer number);

    boolean existsByName(String name);

    boolean existsByPlace(String place);




}
