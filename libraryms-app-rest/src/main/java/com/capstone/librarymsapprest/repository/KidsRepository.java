package com.capstone.librarymsapprest.repository;

import com.capstone.librarymsapprest.model.Book;
import com.capstone.librarymsapprest.model.KidsBook;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KidsRepository extends MongoRepository<KidsBook, String> {
    List<KidsBook> findAll();

    Optional<KidsBook> findById(String id);

    List<KidsBook> findByGenreIgnoreCase(String genre);

    List<KidsBook> findByTitleIgnoreCaseContainingOrAuthorIgnoreCaseContainingOrIsbnIgnoreCaseContaining(String title, String author, String isbn);
}
