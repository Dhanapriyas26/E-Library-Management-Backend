package com.capstone.librarymsapprest.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.capstone.librarymsapprest.model.Review;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReviewRepository extends MongoRepository<Review, ObjectId> {
    List<Review> findAll();
    Optional<Review> findById(ObjectId id);
}
