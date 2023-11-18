package com.library.Library.persistence.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Book {

    private Long id;
    private String title;
    private Author author;

    public Book(Book book) {
        this(book.getId(), book.getTitle(), book.getAuthor());
    }

    @Override
    public String toString() {
        return ("id: " + this.id + ", title: " + this.title + ", author: ");
    }

}
