package dev.thesarfo.lexchange.model.dto.request.book;

import dev.thesarfo.lexchange.entity.user.User;
import dev.thesarfo.lexchange.model.enums.BookStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.Set;

public record BookRequest(
        @NotBlank String title,
        @NotBlank String description,
        @NotBlank String author,
        @NotEmpty Set<String> category,
        BookStatus status,
        User user
) {
}
