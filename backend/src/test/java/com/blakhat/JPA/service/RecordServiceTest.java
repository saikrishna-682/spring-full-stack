package com.blakhat.JPA.service;

import com.blakhat.JPA.exception.ResourceNotFound;
import com.blakhat.JPA.exception.ResourceNotModified;
import com.blakhat.JPA.model.Record;
import com.blakhat.JPA.repository.RecordRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RecordServiceTest {

    private RecordService underTest;
//    private AutoCloseable autoCloseable;
    @Mock
    private RecordRepository recordRepository;
    @BeforeEach
    void setUp() {
//        autoCloseable = MockitoAnnotations.openMocks(this);
        underTest = new RecordService(recordRepository);
    }

//    @AfterEach
//    void tearDown() throws Exception {
//        autoCloseable.close();
//    }

    @Test
    void getRecords() {
        //WHEN
        underTest.getRecords();
        //THEN
        Mockito.verify(recordRepository)
                .findAll();
    }

    @Test
    void getRecordId() {
        // GIVEN
        long id = 1;
        Record record = new Record();
        record.setId(id);
        when(recordRepository.findById(id)).thenReturn(Optional.of(record));

        // WHEN
        Optional<Record> result = Optional.of(underTest.getRecordId(id).orElseThrow());
        result.get();

        // THEN

        Mockito.verify(recordRepository).findById(id);
    }
    @Test
    void getRecordId_recordNotFound() {
        // GIVEN
        long id = 2;
        when(recordRepository.findById(id)).thenReturn(Optional.empty());

        // WHEN & THEN
        assertThrows(ResourceNotFound.class, () -> underTest.getRecordId(id));
        Mockito.verify(recordRepository).findById(id);
    }

    @Test
    void postRecord() {
        //GIVEN
        Record record = new Record();
        record.setMail("example@example.com");
        Mockito.when(recordRepository.save(Mockito.any(Record.class)))
                .thenReturn(record);

        //WHEN
        Record result = underTest.postRecord(record);

        //THEN
        assertNotNull(result);
        Mockito.verify(recordRepository).save(record);
    }

    @Test
    void existMail() {
        //GIVEN
        String mail= "example@example.com";

        //WHEN
        boolean result = underTest.existMail(mail);

        //THEN
        assertFalse(result);
    }

    @Test
    void deleteRecord() {
        // GIVEN
        long id = 1;
        Record record = new Record();
        record.setId(id);

        // WHEN
        Optional<Record> result = underTest.deleteRecord(id);

        // THEN
        Mockito.verify(recordRepository).deleteById(id);
    }

//    @Test
//    void putRecord() {
//        //GIVEN
//
//        //WHEN
//
//        //THEN
//    }

//    @Test
//    void testPutRecordWhenDataModified() {
//        // Mocking existing record from the database
//        Long existingRecordId = 105L;
//        Record existingRecord = new Record(existingRecordId,
//                                           "krishna",
//                                           "krishna@yahoo.com",
//                                           "Marybelle Willms Nondrinker",
//                                           59);
//        when(recordRepository.findById(existingRecordId)).thenReturn(Optional.of(existingRecord));
//
//        // Create the request to update the existing record
//        Record updateRequest = new Record(existingRecordId,
//                                          "hardware",
//                                          "spoiler@g.com",
//                                          "benjamin",
//                                          65);
//
//        // Call the service method
//        Record updatedRecord = underTest.putRecord(existingRecordId, updateRequest);
//
//        // Verify that the save method of the repository was called with the updated record
//        Mockito.verify(recordRepository).save(updatedRecord);
//
//        // Verify that the updated record has the expected values
//        Assertions.assertEquals(updateRequest.getName(), updatedRecord.getName());
//        Assertions.assertEquals(updateRequest.getMail(), updatedRecord.getMail());
//        Assertions.assertEquals(updateRequest.getPlace(), updatedRecord.getPlace());
//        Assertions.assertEquals(updateRequest.getNumber(), updatedRecord.getNumber());
//    }

    @Test
    void testPutRecordWhenDataNotModified() {
        // Mocking existing record from the database
        Long existingRecordId = 105L;
        Record existingRecord = new Record(existingRecordId,
                                           "krishna",
                                           "krishna@yahoo.com",
                                           "Marybelle Willms Ondricka",
                                           59,"male","password");
        when(recordRepository.findById(existingRecordId)).thenReturn(Optional.of(existingRecord));

        // Create the request with the same data as the existing record
        Record updateRequest = new Record(existingRecordId,
                                          "krishna",
                                          "krishna@yahoo.com",
                                          "Marybelle Willms Ondricka",
                                          59,"male","password");

        // Call the service method and expect a ResourceNotModified exception
        assertThrows(ResourceNotModified.class, () -> underTest.putRecord(existingRecordId, updateRequest));

        // Verify that the save method of the repository was not called
        Mockito.verify(recordRepository, never()).save(null);
    }

//    @Test
//    void testPutRecordWhenRecordNotFound() {
//        // Mocking non-existing record from the database
//        Long nonExistingRecordId = 1L;
//        when(recordRepository.findById(nonExistingRecordId)).thenReturn(Optional.empty());
//
//        // Create the request to update the non-existing record
//        Record updateRequest = new Record(nonExistingRecordId, "New Name", "new@mail.com", "New Place", 98765);
//
//        // Call the service method and expect it to return null
//        Record updatedRecord = underTest.putRecord(nonExistingRecordId, updateRequest);
//        assertNull(updatedRecord);
//
//        // Verify that the save method of the repository was not called
//        Mockito.verify(recordRepository, never()).save(null);
//    }

}