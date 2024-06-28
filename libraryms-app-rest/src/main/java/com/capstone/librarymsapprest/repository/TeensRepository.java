package com.capstone.librarymsapprest.repository;

import com.capstone.librarymsapprest.model.KidsBook;
import com.capstone.librarymsapprest.model.TeensBook;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface TeensRepository extends MongoRepository<TeensBook, String> {
    List<TeensBook> findAll();

    Optional<TeensBook> findById(String id);

    List<TeensBook> findByGenreIgnoreCase(String genre);

    List<TeensBook> findByTitleIgnoreCaseContainingOrAuthorIgnoreCaseContainingOrIsbnIgnoreCaseContaining(String title, String author, String isbn);
}
