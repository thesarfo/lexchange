package dev.thesarfo.lexchange.model.dto.request.profile;

public record UpdateProfileRequest(
        String username,
        String email,
        String bio,
        String profilePhoto
) {
}
