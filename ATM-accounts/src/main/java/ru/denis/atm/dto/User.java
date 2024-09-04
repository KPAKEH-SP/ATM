package ru.denis.atm.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
    private Long id;
    private String login;
    private String password;
    private String email;
    private FullName fullName;
}
