package dev.thesarfo.lexchange.repository.book;

import dev.thesarfo.lexchange.entity.book.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
}
