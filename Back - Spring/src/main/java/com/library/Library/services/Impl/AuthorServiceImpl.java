package com.library.Library.services.Impl;

import com.library.Library.persistence.models.Author;
import com.library.Library.persistence.models.Book;
import com.library.Library.persistence.repositories.IAuthorRepository;
import com.library.Library.services.IAuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements IAuthorService {

    @Autowired
    private IAuthorRepository authorRepository;

    @Override
    public Optional<Author> findById(Long id) {
        return authorRepository.findById(id);
    }

    @Override
    public Author save(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public void add(Author author) {
        // TODO Auto-generated method stub
        authorRepository.add(author);
    }

    @Override
    public List<Author> getAllAuthors() {
        // TODO Auto-generated method stub
        return authorRepository.getAllAuthors();
    }

    @Override
    public void deleteAuthor(Long id) { //Se borraran en cascada los libros dentro de la editorial
        // TODO Auto-generated method stub
        authorRepository.deleteAuthor(id);
    }
/*
    @Override
    public void addBook(Long id,Book book) {
        bookService.add(book);
        authorRepository.addBook(id, book);
    }

    @Override
    public String getBooks(Long id) {
        return authorRepository.getBooks(id);
    }

 */

    @Autowired
    public void setEditorialRepository(IAuthorRepository editorialRepository) {
        this.authorRepository = editorialRepository;
    }

    public AuthorServiceImpl(IAuthorRepository editorialRepository) {
        this.authorRepository = editorialRepository;
    }

}
