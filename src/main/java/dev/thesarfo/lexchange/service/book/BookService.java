package dev.thesarfo.lexchange.service.book;


import dev.thesarfo.lexchange.entity.book.Book;
import dev.thesarfo.lexchange.model.dto.request.book.BookRequest;
import dev.thesarfo.lexchange.model.dto.response.book.BookResponse;

import java.util.List;

public interface BookService {
    BookResponse createBook(BookRequest book);
    List<BookResponse> viewAllBooks();
}
