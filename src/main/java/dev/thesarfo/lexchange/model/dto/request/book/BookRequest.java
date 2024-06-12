package dev.thesarfo.lexchange.model.dto.request.book;

import jakarta.validation.constraints.NotBlank;

import java.util.Set;

public record BookRequest(
        @NotBlank String title,
        @NotBlank String description,
        @NotBlank String author,
        Set<String> category
) {
}
