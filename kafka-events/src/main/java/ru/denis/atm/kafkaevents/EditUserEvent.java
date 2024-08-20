package ru.denis.atm.kafkaevents;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class EditUserEvent {
    private String login;
    private boolean isDeleted;
}
