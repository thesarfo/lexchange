package dev.thesarfo.lexchange.controller.book;

import dev.thesarfo.lexchange.model.dto.request.book.BookRequest;
import dev.thesarfo.lexchange.model.success.SuccessMessages;
import dev.thesarfo.lexchange.service.book.BookServiceImpl;
import dev.thesarfo.lexchange.util.ResponseHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/books")
public class BookController {

    private final BookServiceImpl bookService;

    @PostMapping
    public ResponseEntity<Object> createBook(@Valid @RequestBody BookRequest bookRequest){
        return ResponseHandler.response(SuccessMessages.BOOK_CREATED, HttpStatus.OK,
                bookService.createBook(bookRequest));
    }
}
