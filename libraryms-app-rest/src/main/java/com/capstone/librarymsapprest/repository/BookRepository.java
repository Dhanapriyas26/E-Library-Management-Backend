package com.capstone.librarymsapprest.repository;

import com.capstone.librarymsapprest.model.Book;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends MongoRepository<Book, String> {
    List<Book> findAll();

    Optional<Book> findById(String id);

    List<Book> findByGenreIgnoreCase(String genre);

    List<Book> findByTitleIgnoreCaseContainingOrAuthorIgnoreCaseContainingOrIsbnIgnoreCaseContaining(String title, String author, String isbn);
}
