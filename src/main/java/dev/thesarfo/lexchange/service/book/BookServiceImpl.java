package dev.thesarfo.lexchange.service.book;

import dev.thesarfo.lexchange.entity.book.Book;
import dev.thesarfo.lexchange.entity.book.Category;
import dev.thesarfo.lexchange.entity.user.User;
import dev.thesarfo.lexchange.exception.user.UserNotFoundException;
import dev.thesarfo.lexchange.model.dto.request.book.BookRequest;
import dev.thesarfo.lexchange.model.dto.response.book.BookResponse;
import dev.thesarfo.lexchange.model.enums.BookStatus;
import dev.thesarfo.lexchange.repository.book.BookRepository;
import dev.thesarfo.lexchange.repository.book.CategoryRepository;
import dev.thesarfo.lexchange.util.GeneralUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>This class contains all the methods that are used in relation to a book</p>
 *
 * @author Ernest Sarfo
 * @since 1.0
 */
@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private static final Logger log = LoggerFactory.getLogger(BookServiceImpl.class);
    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;
    private final GeneralUtil generalUtil;

    /**
     *
     * @param bookRequest minimum details needed in order to post a book
     * @return details of the just posted book
     * @throws UserNotFoundException if the user making the request is non-existent
     *
     */
    @Override
    @Transactional
    public BookResponse createBook(BookRequest bookRequest) {
        String username = generalUtil.getUsernameFromSecurityContext();
        User user = generalUtil.getUserOrThrowException(username);
        Set<Category> categories = categoryRepository.findByNameIn(bookRequest.category());
        Book book = Book.builder()
                .title(bookRequest.title())
                .description(bookRequest.description())
                .author(bookRequest.author())
                .categories(categories)
                .status(BookStatus.AVAILABLE)
                .user(user)
                .build();

        Book savedBook = bookRepository.save(book);

        return createBookResponseDto(savedBook);
    }


    /**
     *
     * @param page a pageable declaration of how the books will be returnd in the respsonse
     * @return a list of all books that the user has posted themselves
     */
    @Override
    public Page<BookResponse> viewAllBooks(Pageable page) {
        String username = generalUtil.getUsernameFromSecurityContext();
        User user = generalUtil.getUserOrThrowException(username);
        Page<Book> books = bookRepository.findAllByUser(user, page);
        return books.map(BookServiceImpl::createBookResponseDto);
    }

    private static BookResponse createBookResponseDto(Book savedBook) {
        return new BookResponse(
                savedBook.getId(),
                savedBook.getTitle(),
                savedBook.getDescription(),
                savedBook.getAuthor(),
                savedBook.getCategories().stream()
                        .map(Category::getName)
                        .collect(Collectors.toSet()),
                savedBook.getStatus(),
                savedBook.getUser().getId()
        );
    }
}
