package dev.thesarfo.lexchange.model.dto.request.user;

import dev.thesarfo.lexchange.model.error.ErrorMessages;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record UserRegisterRequest(
        @Email(message = ErrorMessages.VALID_EMAIL_ADDRESS)
        @Size(min = 7, message = ErrorMessages.MINIMUM_EMAIL_LENGTH)
        String email,


        @Size(min = 7, message = ErrorMessages.MINIMUM_USERNAME_LENGTH)
        String username,

        @Size(min = 8)
        String password
) {
}
