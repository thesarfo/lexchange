package dev.thesarfo.lexchange.repository.book;

import dev.thesarfo.lexchange.entity.book.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Category findByName(String name);
    Set<Category> findByNameIn(Set<String> names);
}
