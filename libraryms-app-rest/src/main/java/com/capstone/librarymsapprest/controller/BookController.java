package com.capstone.librarymsapprest.controller;

import com.capstone.librarymsapprest.model.Book;
import com.capstone.librarymsapprest.repository.BookRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/ping")
    public String ping() {
        return "Up -books";
    }

    @GetMapping("/books")
    public List<Book> getAllBooks(@RequestParam(required = false) String genre) {
        if (genre == null || genre.equalsIgnoreCase("All")) {
            return bookRepository.findAll();
        } else {
            return bookRepository.findByGenreIgnoreCase(genre);
        }
    }

    @GetMapping("/book/{id}")
    public Optional<Book> getBookById(@PathVariable String id) {
        ObjectId objectId = new ObjectId(String.valueOf(id));
        return bookRepository.findById(id);
    }

    @PostMapping("/book")
    public Book saveBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @DeleteMapping("/book/{id}")
    public void deleteBookById(@PathVariable String id) {
        bookRepository.deleteById(id);
    }

    @PutMapping("/book")
    public Book updateBook(@RequestBody Book book) {
        bookRepository.save(book);
        return book;
    }

    @PutMapping("/book/{id}")
    Book updateBookById(@RequestBody Book newBook, @PathVariable String id) {

        return bookRepository.findById(id).map(book -> {
            book.setTitle(newBook.getTitle());
            book.setAuthor(newBook.getAuthor());
            book.setRating(newBook.getRating());
            book.setIsbn(newBook.getIsbn());
            book.setGenre(newBook.getGenre());
            return bookRepository.save(book);
        }).orElseGet(() -> {
            newBook.setId(id);
            return bookRepository.save(newBook);
        });
    }

    @GetMapping("/books/search")
    public List<Book> findByAnyString(@RequestParam(required = false) String query) {
        return bookRepository.findByTitleIgnoreCaseContainingOrAuthorIgnoreCaseContainingOrIsbnIgnoreCaseContaining(query, query, query);
    }

}
