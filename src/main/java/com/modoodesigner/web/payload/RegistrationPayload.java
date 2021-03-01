package com.modoodesigner.web.payload;
import com.modoodesigner.domain.application.commands.UserRegisterCommand;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class RegistrationPayload {

    @Size(min = 4, max = 20, message = "유저이름은 4자 이상 20자 이하여야합니다.")
    @NotNull
    private String username;

    @Email
    @Size(max = 100, message = "이메일은 100자 이내여야 합니다.")
    @NotNull
    private String emailAddress;

    @Size(min = 6, max = 30, message = "비밀번호는 6자 이상 30자 이하여야합니다.")
    @NotNull
    private String password;

    public UserRegisterCommand toCommand() {
        return new UserRegisterCommand(this.username, this.emailAddress, this.password);
    }


}
