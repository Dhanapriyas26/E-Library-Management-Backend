package com.capstone.librarymsapprest.controller;

import com.capstone.librarymsapprest.model.TeensBook;
import com.capstone.librarymsapprest.repository.TeensRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class TeensBookController {
    @Autowired
    private TeensRepository teensRepository;

    @GetMapping("/teen-books")
    public List<TeensBook> getAllBooks(@RequestParam(required = false) String genre) {
        if (genre == null || genre.equalsIgnoreCase("All")) {
            return teensRepository.findAll();
        } else {
            return teensRepository.findByGenreIgnoreCase(genre);
        }
    }

    @GetMapping("/teen-book/{id}")
    public Optional<TeensBook> getBookById(@PathVariable String id) {
        ObjectId objectId = new ObjectId(String.valueOf(id));
        return teensRepository.findById(id);
    }

    @PostMapping("/teen-book")
    public TeensBook saveBook(@RequestBody TeensBook TeensBook) {
        return teensRepository.save(TeensBook);
    }

    @DeleteMapping("/teen-book/{id}")
    public void deleteBookById(@PathVariable String id) {
        teensRepository.deleteById(id);
    }

    @PutMapping("/teen-book")
    public TeensBook updateBook(@RequestBody TeensBook book) {
        teensRepository.save(book);
        return book;
    }

    @PutMapping("/teen-book/{id}")
    TeensBook updateBookById(@RequestBody TeensBook newBook, @PathVariable String id) {

        return teensRepository.findById(id).map(book -> {
            book.setTitle(newBook.getTitle());
            book.setAuthor(newBook.getAuthor());
            book.setRating(newBook.getRating());
            book.setIsbn(newBook.getIsbn());
            book.setGenre(newBook.getGenre());
            return teensRepository.save(book);
        }).orElseGet(() -> {
            newBook.setId(id);
            return teensRepository.save(newBook);
        });
    }

    @GetMapping("/teen-book/search")
    public List<TeensBook> findByAnyString(@RequestParam(required = false) String query) {
        return teensRepository.findByTitleIgnoreCaseContainingOrAuthorIgnoreCaseContainingOrIsbnIgnoreCaseContaining(query, query, query);
    }
}
