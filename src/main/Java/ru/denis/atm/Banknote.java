package ru.denis.atm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "banknotes")
@Getter
@Setter
@ToString
public class Banknote {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "count")
    private int count;
}
