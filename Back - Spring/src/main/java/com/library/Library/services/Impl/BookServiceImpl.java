package com.library.Library.services.Impl;

import com.library.Library.persistence.models.Book;
import com.library.Library.persistence.repositories.BookRepository;
import com.library.Library.services.IBookService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements IBookService {

  @Autowired
  private BookRepository bookRepository;

  @Override
  public Optional<Book> findById(Long id) {
    return bookRepository.findById(id);
  }

  @Override
  public Book save(Book book) {
    return bookRepository.save(book);
  }


  @Override
  public List<Book> getAllBooks() {
    // TODO Auto-generated method stub
    return bookRepository.findAll();
  }

  @Override
  public void deleteBook(Long id) {
    Optional<Book> bookOptional = bookRepository.findById(id);

    if (bookOptional.isPresent()) {
      Book book = bookOptional.get();
      bookRepository.delete(book);
    } else {
      // Manejar el caso cuando el libro no existe
      throw new EntityNotFoundException("Book with id " + id + " not found");
    }
  }

  @Override
  public List<Book> getAuthorBooks(Long authorId) {
    return bookRepository.findByAuthorId(authorId);
  }
}
