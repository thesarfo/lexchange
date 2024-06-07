package dev.thesarfo.lexchange.model.dto.response.user;

import dev.thesarfo.lexchange.model.enums.UserRole;

import java.time.LocalDateTime;

public record UserResponseDto(
        String id,
        String email,
        String username,
        LocalDateTime createdAt,
        UserRole role
){
}
