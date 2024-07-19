package dev.thesarfo.lexchange.model.dto.response.book;

import java.util.Set;

public record BookResponse(
        Integer id,
        String title,
        String description,
        String author,
        Set<String> categories,
        String userId
) {
}
