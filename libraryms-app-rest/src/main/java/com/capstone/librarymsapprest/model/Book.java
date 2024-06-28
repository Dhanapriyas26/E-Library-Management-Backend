package com.capstone.librarymsapprest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection="product-service")
public class Book {
    @Id
    private String id;
    private String title;
    private String author;
    private int rating;
    private String isbn;
    private String genre;
    private String pdfUrl;

    private List<Integer> ratings;
}
