package com.blakhat.JPA.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
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
public class Record implements UserDetails {

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
    @Column(nullable = false)
    private String password;

//    public Record(String name, String mail, String place, int number) {
//
//    }


//    public String mail() {
//        return this.mail;
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return mail;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Record record = (Record) o;
        return Objects.equals(id, record.id) && Objects.equals(name, record.name) && Objects.equals(mail, record.mail) && Objects.equals(place, record.place) && Objects.equals(number, record.number) && Objects.equals(gender, record.gender) && Objects.equals(password, record.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, mail, place, number, gender, password);
    }
}
