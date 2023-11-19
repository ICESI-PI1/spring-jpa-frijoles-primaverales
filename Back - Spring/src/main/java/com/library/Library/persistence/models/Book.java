package com.library.Library.persistence.models;

import jakarta.persistence.*;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public Book(Book book) {
        this(book.getId(), book.getTitle(), book.getAuthor());
    }

    @Override
    public String toString() {
        return ("id: " + this.id + ", title: " + this.title + ", author: " + this.author.getName());
    }

}
