package dev.thesarfo.lexchange.model.dto.response.book;

import dev.thesarfo.lexchange.model.enums.BookStatus;

import java.util.Set;

public record BookResponse(
        Integer id,
        String title,
        String description,
        String author,
        Set<String> categories,
        BookStatus status,
        String userId
) {
}
