package dev.thesarfo.lexchange.repository.book;

import dev.thesarfo.lexchange.entity.book.Book;
import dev.thesarfo.lexchange.entity.user.User;
import dev.thesarfo.lexchange.model.dto.response.book.BookResponse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {
    List<Book> findAllByUser(User user);

}
