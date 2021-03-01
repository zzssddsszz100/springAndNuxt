package com.modoodesigner.domain.application.commands;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode(callSuper = false)
public class UserRegisterCommand extends AnonymousCommand{

    private final String username;
    private final String emailAddress;
    private final String password;

}
