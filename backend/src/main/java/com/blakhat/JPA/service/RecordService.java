package com.blakhat.JPA.service;

import com.blakhat.JPA.exception.ResourceNotFound;
import com.blakhat.JPA.exception.ResourceNotModified;
import com.blakhat.JPA.model.Record;
import com.blakhat.JPA.model.RecordRegistrationRequest;
import com.blakhat.JPA.repository.RecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class RecordService {




    @Autowired
    private final RecordRepository recordRepository;

    public RecordService(RecordRepository recordRepository) {
        this.recordRepository = recordRepository;
    }

    // GET ALL THE RECORDS
    public List<Record> getRecords() {
        return recordRepository.findAll();
    }
    // PULL RECORDS BASED ON ID'S
    public Optional<Record> getRecordId(Long id) {
        return Optional.ofNullable(recordRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("there is no such record with ID: [%s]".formatted(id))));
    }
    //POST RECORD INTO THE DB WITH VALIDATIONS
    public Record postRecord(Record record) {
        if(record.getMail() == null || record.getMail().isEmpty()){
            throw new IllegalArgumentException("Email is required");
        }
        if(existMail(record.getMail())){
            throw new IllegalArgumentException("Email already exists");
        }
        return recordRepository.save(record);
    }
    // this checks whether the email already exists or not within the DB while posting
    public boolean existMail(String mail){
        return recordRepository.existsByMail(mail);
    }
    // DELETE RECORD FROM THE DB
    public Optional<Record> deleteRecord(Long id) {
         recordRepository.deleteById(id);
        return Optional.empty();
    }

    // PUT Request IN ORDER TO EDIT THE RECORD BASED ON ID
    public Record putRecord(Long id, Record record) {
        Optional<Record> existingRecord = recordRepository.findById(id);
        if(existingRecord.isPresent()){
            Record existingNewRecord = existingRecord.get();
//           // existingNewRecord.setId(record.getId());
//            existingNewRecord.setName(record.getName());
//            existingNewRecord.setMail(record.getMail());
//            existingNewRecord.setPlace(record.getPlace());
//            existingNewRecord.setNumber(record.getNumber());

            if(!isExistingRecordModified(existingNewRecord, record)){
                throw new ResourceNotModified("Data has not been modified");
            }
            // Update only the fields that have changed
            if (record.getName() != null) {
                existingNewRecord.setName(record.getName());
            }
            if (record.getMail() != null) {
                existingNewRecord.setMail(record.getMail());
            }
            if (record.getPlace() != null) {
                existingNewRecord.setPlace(record.getPlace());
            }
            if (record.getNumber() != null) {
                existingNewRecord.setNumber(record.getNumber());
            }


//            if(numberChange(record.getNumber())){
//                throw new IllegalArgumentException("no change in phone number");
//            }
//            if(placeChange(record.getPlace())){
//                throw new IllegalArgumentException("no change in place");
//            }
//            if(mailChange(record.getMail())){
//                throw new IllegalArgumentException("no change in mail address");
//            }if(nameChange(record.getName())){
//                throw new IllegalArgumentException("no change in name");
//            }

            return recordRepository.save(existingNewRecord);
        }
        else{
            throw new ResourceNotFound("Record with ID: " + id + " not found");
        }
    }

    private boolean numberChange(Integer number){
        return recordRepository.existsByNumber(number);
    }
    private boolean nameChange(String name){
        return recordRepository.existsByName(name);
    }
    private boolean placeChange(String place){
        return recordRepository.existsByPlace(place);
    }
    private boolean mailChange(String mail){
        return recordRepository.existsByMail(mail);
    }



    private boolean isExistingRecord( Record presrentRecord, Record oldRecord){
        return !presrentRecord.equals(oldRecord);
    }

    private boolean isExistingRecordModified(Record existingRecord, Record newRecord) {
        // Compare each field of the existing and new records
        boolean isNameModified = !Objects.equals(existingRecord.getName(), newRecord.getName());
        boolean isMailModified = !Objects.equals(existingRecord.getMail(), newRecord.getMail());
        boolean isPlaceModified = !Objects.equals(existingRecord.getPlace(), newRecord.getPlace());
        boolean isNumberModified = !Objects.equals(existingRecord.getNumber(), newRecord.getNumber());

        // Return true if any field has changed
        return isNameModified || isMailModified || isPlaceModified || isNumberModified;
    }


//    public RecordRegistrationRequest insertRecord(RecordRegistrationRequest request) {
//
//        return recordRepository.save(request);
//    }
}
