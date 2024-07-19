package dev.thesarfo.lexchange.util;

import dev.thesarfo.lexchange.entity.book.Category;
import dev.thesarfo.lexchange.repository.book.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Seeder implements CommandLineRunner {
    private static final Logger log = LoggerFactory.getLogger(Seeder.class);
    private final CategoryRepository categoryRepository;

    @Override
    public void run(String... args) {
        if (categoryRepository.count() == 0) {
            categoryRepository.save(new Category("Fantasy"));
            categoryRepository.save(new Category("Adventure"));
            categoryRepository.save(new Category("Mystery"));
            categoryRepository.save(new Category("Horror"));
            categoryRepository.save(new Category("Literary Fiction"));
            categoryRepository.save(new Category("Romance"));
            categoryRepository.save(new Category("Finance"));
            categoryRepository.save(new Category("Magazine"));
            categoryRepository.save(new Category("Biography"));
            categoryRepository.save(new Category("Memoir"));
            categoryRepository.save(new Category("Sci-Fi"));
            categoryRepository.save(new Category("Action"));
            categoryRepository.save(new Category("Comedy"));
            categoryRepository.save(new Category("Humor"));
        } else {
            log.info("Categories already exist in the database");
        }
    }
}
