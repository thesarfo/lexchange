package dev.thesarfo.lexchange.model.dto.request.user;

import jakarta.validation.constraints.NotBlank;

public record ChangePasswordRequest (
        @NotBlank String oldPassword,
        @NotBlank String newPassword
){
}
