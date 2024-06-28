package com.capstone.librarymsapprest.controller;

import com.capstone.librarymsapprest.model.KidsBook;
import com.capstone.librarymsapprest.repository.KidsRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class KidBookController {

    @Autowired
    private KidsRepository kidsRepository;

    @GetMapping("/Kids-books")
    public List<KidsBook> getAllBooks(@RequestParam(required = false) String genre) {
        if (genre == null || genre.equalsIgnoreCase("All")) {
            return kidsRepository.findAll();
        } else {
            return kidsRepository.findByGenreIgnoreCase(genre);
        }
    }

    @GetMapping("/Kids-book/{id}")
    public Optional<KidsBook> getBookById(@PathVariable String id) {
        ObjectId objectId = new ObjectId(String.valueOf(id));
        return kidsRepository.findById(id);
    }
    @PostMapping("/Kids-book")
    public KidsBook saveBook(@RequestBody KidsBook kidsBook) {
        return kidsRepository.save(kidsBook);
    }

    @DeleteMapping("/Kids-book/{id}")
    public void deleteBookById(@PathVariable String id) {
        kidsRepository.deleteById(id);
    }

    @PutMapping("/Kids-book")
    public KidsBook updateBook(@RequestBody KidsBook book) {
        kidsRepository.save(book);
        return book;
    }

    @PutMapping("/Kids-book/{id}")
    KidsBook updateBookById(@RequestBody KidsBook newBook, @PathVariable String id) {

        return kidsRepository.findById(id).map(book -> {
            book.setTitle(newBook.getTitle());
            book.setAuthor(newBook.getAuthor());
            book.setRating(newBook.getRating());
            book.setIsbn(newBook.getIsbn());
            book.setGenre(newBook.getGenre());
            return kidsRepository.save(book);
        }).orElseGet(() -> {
            newBook.setId(id);
            return kidsRepository.save(newBook);
        });
    }

    @GetMapping("/Kids-book/search")
    public List<KidsBook> findByAnyString(@RequestParam(required = false) String query) {
        return kidsRepository.findByTitleIgnoreCaseContainingOrAuthorIgnoreCaseContainingOrIsbnIgnoreCaseContaining(query, query, query);
    }
}
