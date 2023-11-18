package com.library.Library.services.Impl;

import com.library.Library.persistence.models.Book;
import com.library.Library.persistence.repositories.IBookRepository;
import com.library.Library.services.IBookService;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements IBookService {

  @Autowired
  private IBookRepository bookRepository;

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
    return bookRepository.getAllBooks();
  }

  @Override
  public void deleteBook(Long id) {
    // TODO Auto-generated method stub
    bookRepository.deleteBook(id);
  }

  @Override
  public List<Book> getAuthorBooks(Long id) {
    return bookRepository.getAuthorBooks(id);
  }

  @Autowired
  public void setProjectRepository(IBookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  public BookServiceImpl(IBookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }
}
