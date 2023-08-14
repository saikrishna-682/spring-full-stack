package com.blakhat.JPA.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Table(
        name = "record",
        uniqueConstraints = {
               @UniqueConstraint (
                        name = "record_mail_unique",
                columnNames = "mail"
                )

        }
)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Record {

    @Id
    @SequenceGenerator(name = "record_id_seq",
    sequenceName="record_id_seq",allocationSize = 1)
    @GeneratedValue(strategy= GenerationType.AUTO,
    generator = "record_id_seq")
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String mail;
    @Column(nullable = false)
    private String place;
    @Column(nullable = false)
    private Integer number;

    @Column(nullable = false)
    private String gender;

//    public Record(String name, String mail, String place, int number) {
//
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return Objects.equals(id, record.id) && Objects.equals(name, record.name) && Objects.equals(mail, record.mail) && Objects.equals(place, record.place) && Objects.equals(number, record.number) && Objects.equals(gender, record.gender);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, mail, place, number,gender);
    }
}
