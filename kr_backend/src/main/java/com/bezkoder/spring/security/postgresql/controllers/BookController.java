package com.bezkoder.spring.security.postgresql.controllers;

import com.bezkoder.spring.security.postgresql.models.Book;
import com.bezkoder.spring.security.postgresql.payload.request.BookRequest;
import com.bezkoder.spring.security.postgresql.payload.response.MessageResponse;
import com.bezkoder.spring.security.postgresql.repository.BookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookRepository bookRepository;
    private final ModelMapper modelMapper;

    public BookController(BookRepository bookRepository, ModelMapper modelMapper) {
        this.bookRepository = bookRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/info")
    public String allAccess() {
        return "Клиент-серверное фуллстек-приложение книжного магазина для хранения складских запасов.";
    }

    @GetMapping("/book")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public String userAccess() {
        return "User Content.";
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }

    @GetMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")

    public ResponseEntity<List<Book>> getAllBooks() {
        return ResponseEntity.ok(bookRepository.findAll());
    }

    @GetMapping("/search")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<List<Book>> getAllBooksBySearchWord(@RequestParam String searchWord) {
        System.out.println(bookRepository.getBooksSearch(searchWord));
        return ResponseEntity.ok(bookRepository.getBooksSearch(searchWord));
    }

    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> saveBook(@RequestBody @Valid BookRequest bookRequest) {
        if (bookRepository.existsBookByTitleAndAuthorNameAndPublisher(
                bookRequest.getTitle(),
                bookRequest.getAuthorName(),
                bookRequest.getPublisher()
        )) return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error: Такое книга уже существует!"));

        Book book = convertToBookDTO(bookRequest);
        return ResponseEntity.ok(bookRepository.save(book));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> updateBook(@RequestBody @Valid BookRequest bookRequest,
                                        @PathVariable Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        if(bookOptional.isEmpty()) return ResponseEntity.badRequest()
                .body(new MessageResponse("Error: Книга не существует"));

        Book book = bookOptional.get();
        book.setTitle(bookRequest.getTitle());
        book.setAuthorName(bookRequest.getAuthorName());
        book.setPublisher(bookRequest.getPublisher());
        book.setPublishYear(bookRequest.getPublishYear());
        book.setCount(bookRequest.getCount());
        book.setPrice(bookRequest.getPrice());

        return ResponseEntity.ok(bookRepository.save(book));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public void deleteBook(@PathVariable Long id) {
        bookRepository.deleteById(id);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<?> findBookById(@PathVariable Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);
        return bookOptional.isPresent() ?
                ResponseEntity.ok(bookOptional.get()) :
                ResponseEntity.badRequest().body(new MessageResponse("Error: Книга не существует"));
    }

    public Book convertToBookDTO(BookRequest bookDTO) {
        return this.modelMapper.map(bookDTO, Book.class);
    }
}
