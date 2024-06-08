package dev.thesarfo.lexchange.model.dto.request.user;

import dev.thesarfo.lexchange.model.error.ErrorMessages;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserRegisterRequest(
        @NotBlank
        @Email(message = ErrorMessages.VALID_EMAIL_ADDRESS)
        @Size(min = 7, message = ErrorMessages.MINIMUM_EMAIL_LENGTH)
        String email,


        @NotBlank
        @Size(min = 7, message = ErrorMessages.MINIMUM_USERNAME_LENGTH)
        String username,

        @NotBlank
        @Size(min = 8, message = ErrorMessages.MINIMUM_PASSWORD_LENGTH)
        String password
) {
}
