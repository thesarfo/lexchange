package dev.thesarfo.lexchange.model.dto.response.book;

import java.util.Set;

public record BookResponse(
        Long id,
        String title,
        String description,
        String author,
        Set<String> categories

) {
}
