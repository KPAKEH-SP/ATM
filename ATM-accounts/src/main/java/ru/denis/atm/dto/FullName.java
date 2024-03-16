package ru.denis.atm.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class FullName implements Serializable {
    private String name;
    private String surname;
    private String patronymic;
}
