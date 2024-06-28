package com.capstone.librarymsapprest.controller;

import com.capstone.librarymsapprest.model.Review;
import com.capstone.librarymsapprest.repository.ReviewRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@ResponseBody
@CrossOrigin(origins = "*")
public class ReviewController {

    @Autowired
    ReviewRepository reviewRepository;

    @GetMapping("/reviews/{id}")
    public Optional<Review> getReviewById(@PathVariable ObjectId id) {
        return reviewRepository.findById(id);
    }

    @GetMapping("/reviews")
    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    @PostMapping("/review")
    public Optional<Review> postReview(@RequestBody Review review) {
        return Optional.of(reviewRepository.save(review));
    }

}
