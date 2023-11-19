package com.library.Library.persistence.models;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @Temporal(TemporalType.TIMESTAMP)
    private Date publicationDate;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    public Book(Book book) {
        this(book.getId(), book.getTitle(), book.getPublicationDate(), book.getAuthor());
    }

    @Override
    public String toString() {
        return ("id: " + this.id + ", title: " + this.title + ", publication: "+ this.publicationDate +", author: " + this.author.getName());
    }

}
