package com.library.Library.persistence.repositories.Impl;

import com.library.Library.LibraryApplication;
import com.library.Library.persistence.models.Author;
import com.library.Library.persistence.models.Book;
import com.library.Library.persistence.repositories.IBookRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class BookRepositoryImpl implements IBookRepository {

    private Logger log = LoggerFactory.getLogger(LibraryApplication.class);

    private List<Book> books = new ArrayList<>();

    public BookRepositoryImpl() {
        super();
    }

    @Override
    public Optional<Book> findById(Long id){
        Optional<Book> match = books.stream().filter(p-> Objects.equals(p.getId(), id)).findFirst();
        if(match.isPresent()){
            log.info("Object with id: "+id+" found: " + match.toString());
        }else{
            log.info("Object with id: "+id+" not found");
        }
        return match;
        
    }


    @Override
    public Book save(Book book) {
        Book existingBook = findById(book.getId()).orElse(null);
        if (existingBook == null){
            books.add(book);
        }else{
            books.remove(existingBook);
            Book newProject = new Book(book);
            books.add(newProject);
        }
        log.info("Saving book" + book.toString());
        return book;
    }

    @Override
    public List<Book> getAllBooks() {
        System.out.println("entra");
        log.info("List of books: " + this.books);
        return this.books;
    }

    @Override
    public void deleteBook(Long id) {
        int len = books.size();
        books.removeIf(book -> Objects.equals(book.getId(), id));

        if(len > books.size()){
            log.info("Book deleted");
        }else{
            log.info("Error, book not found");
        }
    }

    @Override
    public List<Book> getAuthorBooks(Long id) {

        return books.stream()
                .filter(book -> {
                    Author author = book.getAuthor();
                    return author != null && Objects.equals(author.getId(), id);
                })
                .collect(Collectors.toList());
    }

}
