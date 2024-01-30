package ru.denis.atm;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "banknotes")
public class BanknoteObject {
    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "count")
    private int count;

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BanknoteObject{" +
                "id=" + id +
                ", count=" + count +
                '}';
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
