package com.library.Library.services;

import com.library.Library.persistence.models.Author;
import com.library.Library.persistence.models.Book;

import java.util.List;
import java.util.Optional;

public interface IAuthorService {
    Optional<Author> findById(Long id);

    Author save(Author author);

    void add(Author author);

    List<Author> getAllAuthors();

    void deleteAuthor(Long id);

    //void addBook(Long id,Book book);

    //String getBooks(Long id);
}
