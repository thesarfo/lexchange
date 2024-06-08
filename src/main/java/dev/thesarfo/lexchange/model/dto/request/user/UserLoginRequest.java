package dev.thesarfo.lexchange.model.dto.request.user;

import jakarta.validation.constraints.NotBlank;

public record UserLoginRequest(
        @NotBlank String username,
        @NotBlank String password
) {
}
