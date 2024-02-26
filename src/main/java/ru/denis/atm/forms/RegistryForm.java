package ru.denis.atm.forms;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RegistryForm {
    public String login;
    public String password;
    public String email;
    public FullName fullName;

    public String getFullName(){
        ObjectMapper mapper = new ObjectMapper();
        String jsonFullName;
        try {
            jsonFullName = mapper.writeValueAsString(fullName);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return jsonFullName;
    }

    public static class FullName{
        public String name;
        public String surname;
        public String patronymic;
    }
}
