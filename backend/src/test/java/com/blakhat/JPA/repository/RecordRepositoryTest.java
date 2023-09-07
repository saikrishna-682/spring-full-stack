package com.blakhat.JPA.repository;

import com.blakhat.JPA.AbstractContainerUnitTest;
import com.blakhat.JPA.model.Record;
import com.blakhat.JPA.service.RecordService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class RecordRepositoryTest extends AbstractContainerUnitTest {



    @Mock
    private  RecordRepository underTest;

    @Autowired
    private ApplicationContext applicationContext;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        underTest.deleteAll();
        Record record1 = new Record(1L,"sai","sai@gmail.com","local",898989898,"male","password");
        Record record2 = new Record(2L,"Jane", "jane@example.com", "Place 2", 898989898,"female","password");
        underTest.save(record1);
        underTest.save(record2);
    }
    @Test
    void testExistsByMail() {
        // Mock the behavior of the repository method
        String mail = "test@example.com";
        when(underTest.existsByMail(mail)).thenReturn(true);

        // Call the repository method
        boolean exists = underTest.existsByMail(mail);

        // Verify the result
        Assertions.assertTrue(exists);
    }

    @Test
    void testExistsByNumber() {
        // Mock the behavior of the repository method
        int number = 12345;
        when(underTest.existsByNumber(number)).thenReturn(true);

        // Call the repository method
        boolean exists = underTest.existsByNumber(number);

        // Verify the result
        Assertions.assertTrue(exists);
    }

    @Test
    void testExistsByName() {
        // Mock the behavior of the repository method
        String name = "John Doe";
        when(underTest.existsByName(name)).thenReturn(true);

        // Call the repository method
        boolean exists = underTest.existsByName(name);

        // Verify the result
        Assertions.assertTrue(exists);
    }

    @Test
    void testExistsByPlace() {
        // Mock the behavior of the repository method
        String place = "New York";
        when(underTest.existsByPlace(place)).thenReturn(true);

        // Call the repository method
        boolean exists = underTest.existsByPlace(place);

        // Verify the result
        Assertions.assertTrue(exists);
    }


}