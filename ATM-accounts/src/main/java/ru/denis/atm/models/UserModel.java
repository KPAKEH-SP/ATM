package ru.denis.atm.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import ru.denis.atm.dto.FullName;

@Entity
@Table(name = "users")
@Getter
@Setter
public class UserModel {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id")
    @SequenceGenerator(name = "id" , sequenceName = "user_id_seq", allocationSize = 0)
    private Long id;

    @Column(name = "login", unique = true)
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "fullname")
    @JdbcTypeCode(SqlTypes.JSON)
    private FullName fullName;

    @Override
    public String toString() {
        return "id: " + id + ", login: " + login + ", email: " + email + ", password: " + password + ", full name: " + fullName.toString() + "\n";
    }
}
