package dev.thesarfo.lexchange.model.dto.request.user;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public record UserRegisterRequest(
        @Email(message = "Please enter valid e-mail address")
        @Size(min = 7, message = "Minimum e-mail length is 7 characters.")
        String email,


        @Size(min = 7, message = "Minimum e-mail length is 7 characters.")
        String username,

        @Size(min = 8)
        String password
) {
}
