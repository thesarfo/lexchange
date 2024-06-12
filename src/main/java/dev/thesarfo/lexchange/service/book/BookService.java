package dev.thesarfo.lexchange.service.book;


import dev.thesarfo.lexchange.model.dto.request.book.BookRequest;
import dev.thesarfo.lexchange.model.dto.response.book.BookResponse;

public interface BookService {
    BookResponse createBook(BookRequest book);
}
