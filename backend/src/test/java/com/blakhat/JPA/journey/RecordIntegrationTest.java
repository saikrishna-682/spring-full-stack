package com.blakhat.JPA.journey;

import com.blakhat.JPA.exception.ResourceNotModified;
import com.blakhat.JPA.model.Record;
import com.blakhat.JPA.model.RecordRegistrationRequest;
import com.blakhat.JPA.model.RecordUpdateRequest;
import com.github.javafaker.Faker;
import com.github.javafaker.Name;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.ErrorResponse;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RecordIntegrationTest {

    @Autowired
    private WebTestClient webTestClient;

    private static final Random random = new Random();

    private static final String CUSTOMER_URI = "/api/v1/records";

    @Test
    void canRegisterACustomer() {
        // todo : create a registry request

//        Long id = 1L;
        Record record = new Record();
        Faker faker = new Faker();

        Name fakerName = faker.name();
//        record.setId(id);
        record.setName(fakerName.lastName());
        record.setMail(fakerName.lastName() + fakerName.firstName()+"@mail.com");
        record.setPlace(fakerName.nameWithMiddle());
        record.setNumber(random.nextInt(1,100));
        record.setGender("male");
        record.setPassword("password");

//        String name = fakerName.fullName();
//        String mail = fakerName.lastName() + fakerName.firstName()+"@mail.com";
//        String place = fakerName.nameWithMiddle();
//        int number = random.nextInt(16,40);


        String name = record.getName();
        String mail = record.getMail();
        String place = record.getPlace();
        int number = record.getNumber();
        String gender = record.getGender();
        String password = record.getPassword();

//        Record newRecord = new Record(
//               fullName,Email, destination, num
//        );


        RecordRegistrationRequest request = new RecordRegistrationRequest(
                name, mail, place, number,gender,password
        );

        // todo : send a post request
        webTestClient.post()
                .uri(CUSTOMER_URI)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(request),RecordRegistrationRequest.class)
                .exchange()
                .expectStatus()
                .isOk();


        // todo : get all customers
        List<Record> allRecords =  webTestClient.get()
                .uri(CUSTOMER_URI)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBodyList(Record.class)
                .returnResult()
                .getResponseBody();


        // todo : make sure the customer is present
        Record expectedRecord = new Record(null,name, mail, place, number,gender,password);

        assertThat(allRecords)
                .usingRecursiveFieldByFieldElementComparatorIgnoringFields("id")
                .contains(record);

        // todo : get customer by id
        Long id = allRecords.stream()
                .filter(record1 -> record1.getMail().equals(mail))
                .map(Record::getId)
                .findFirst()
                .orElseThrow();

        record.setId(id);

        webTestClient.get()
                .uri(CUSTOMER_URI+"/{id}",id)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(Record.class)
                .isEqualTo(record);

        // todo : delete record by id
        webTestClient.delete()
                .uri(CUSTOMER_URI+"/{id}",id)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isOk();

        verifyRecordDoesNotExist(id);

    }

    // todo : Helper method to verify that a record with a given ID does not exist
    private void verifyRecordDoesNotExist(Long id) {
        webTestClient.get()
                .uri(CUSTOMER_URI + "/{id}", id)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus()
                .isNotFound();
    }

//    @Test
//    void canUpdateRecord() {
//        Record record = new Record();
//        Faker faker = new Faker();
//        Name fakerName = faker.name();
//
//        record.setName(fakerName.firstName());
//        record.setMail(fakerName.firstName()+fakerName.lastName()+"@ohaaaaa.yikes");
//        record.setPlace(fakerName.nameWithMiddle());
//        record.setNumber(random.nextInt(1,100));
//
//        String name = record.getName();
//        String mail = record.getMail();
//        String place = record.getPlace();
//        int number = record.getNumber();
//
//        RecordRegistrationRequest request = new RecordRegistrationRequest(name,mail,place,number);
//
//        webTestClient.post()
//                .uri(CUSTOMER_URI)
//                .accept(MediaType.APPLICATION_JSON)
//                .contentType(MediaType.APPLICATION_JSON)
//                .body(Mono.just(request), RecordRegistrationRequest.class)
//                .exchange()
//                .expectStatus()
//                .isOk();
//
//        List<Record> allRecords= webTestClient.get()
//                .uri(CUSTOMER_URI)
//                .accept(MediaType.APPLICATION_JSON)
//                .exchange()
//                .expectStatus()
//                .isOk()
//                .expectBodyList(Record.class)
//                .returnResult()
//                .getResponseBody();
//
//        assertThat(allRecords)
//                .usingRecursiveFieldByFieldElementComparatorIgnoringFields("id")
//                .contains(record);
//
//
//        Long id = allRecords.stream()
//                            .filter(rec -> rec.getMail().equals(mail))
//                            .map(Record::getId)
//                            .findFirst()
//                            .orElseThrow();
//
//
//       // RecordUpdateRequest updateRequest = new RecordUpdateRequest("entryTest",null,null,null);
//        Record record1 = new Record(id,
//                                    fakerName.firstName(),
//                                    fakerName.firstName()+fakerName.lastName()+"@gooooogle.com",
//                                    fakerName.nameWithMiddle(),
//                                    random.nextInt(1,100));
//
////        webTestClient.put()
////                     .uri(CUSTOMER_URI+"/{id}",id)
////                     .accept(MediaType.APPLICATION_JSON)
////                     .contentType(MediaType.APPLICATION_JSON)
////                     .body(Mono.just(record1),Record.class)
////                     .exchange()
////                     .expectStatus()
////                .isOk();
//        webTestClient.put()
//                     .uri(CUSTOMER_URI+"/{id}", id)
//                     .accept(MediaType.APPLICATION_JSON)
//                     .contentType(MediaType.APPLICATION_JSON)
//                     .body(Mono.just(record1), Record.class)
//                     .exchange()
//                     .expectStatus()
//                     .isOk();
//
//
//        Record updatedRecord = webTestClient.get()
//                                           .uri(CUSTOMER_URI + "/{id}", id)
//                                           .accept(MediaType.APPLICATION_JSON)
//                                           .exchange()
//                                           .expectStatus()
//                                           .isOk()
//                                           .expectBody(Record.class)
//                                           .returnResult()
//                                           .getResponseBody();
//
//        Record expectedResult  = new Record(id, "entryTest",mail,place,number);
//
//        assertThat(record1).isEqualTo(expectedResult);
//
//
//    }
    @Test
    void canUpdateRecord() {
        // Create a new record to be updated
        Record record = new Record();
        Faker faker = new Faker();
        Name fakerName = faker.name();
        record.setName(fakerName.firstName());
        record.setMail(fakerName.firstName() + fakerName.lastName() + "@ohaaaaa.yikes");
        record.setPlace(fakerName.nameWithMiddle());
        record.setNumber(random.nextInt(1, 100));
        record.setGender("male");

        // Send a POST request to register the record
        webTestClient.post()
                     .uri(CUSTOMER_URI)
                     .accept(MediaType.APPLICATION_JSON)
                     .contentType(MediaType.APPLICATION_JSON)
                     .body(Mono.just(record), Record.class)
                     .exchange()
                     .expectStatus().isOk();

        // Retrieve the list of all records to find the ID of the record to be updated
        List<Record> allRecords = webTestClient.get()
                                               .uri(CUSTOMER_URI)
                                               .accept(MediaType.APPLICATION_JSON)
                                               .exchange()
                                               .expectStatus().isOk()
                                               .expectBodyList(Record.class)
                                               .returnResult()
                                               .getResponseBody();

        Long id = allRecords.stream()
                            .filter(rec -> rec.getMail().equals(record.getMail()))
                            .map(Record::getId)
                            .findFirst()
                            .orElseThrow();

        // Create the updated record with modified data
        Record updatedRecord = new Record(
                id,
                "UpdatedName",
                "new@extra.loofi",
                "korean",
                867,
                "male",
                "password"
        );

        // Send a PUT request to update the record
        webTestClient.put()
                     .uri(CUSTOMER_URI + "/{id}", id)
                     .accept(MediaType.APPLICATION_JSON)
                     .contentType(MediaType.APPLICATION_JSON)
                     .body(Mono.just(updatedRecord), Record.class)
                     .exchange()
                     .expectStatus().isOk()
                     .expectBody(Record.class)
                     .isEqualTo(updatedRecord);

        // Verify that the record has been updated correctly
        Record updatedRecordFromDB = webTestClient.get()
                                                  .uri(CUSTOMER_URI + "/{id}", id)
                                                  .accept(MediaType.APPLICATION_JSON)
                                                  .exchange()
                                                  .expectStatus().isOk()
                                                  .expectBody(Record.class)
                                                  .returnResult()
                                                  .getResponseBody();

        assertThat(updatedRecordFromDB).isEqualTo(updatedRecord);
    }
}
