package dev.thesarfo.lexchange.repository.book;

import dev.thesarfo.lexchange.entity.book.Book;
import dev.thesarfo.lexchange.entity.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {
    Page<Book> findAllByUser(User user, Pageable pageable);

}
