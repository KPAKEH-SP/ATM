package ru.denis.atm.forms;

import lombok.Getter;
import lombok.Setter;
import ru.denis.atm.dto.FullName;

@Getter
@Setter
public class RegistryForm {
    private String login;
    private String password;
    private String email;
    private FullName fullName;
}
