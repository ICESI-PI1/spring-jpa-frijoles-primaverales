package com.library.Library.services.Impl;

import com.library.Library.persistence.models.Author;
import com.library.Library.persistence.repositories.AuthorRepository;
import com.library.Library.services.IAuthorService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorServiceImpl implements IAuthorService {

    @Autowired
    private AuthorRepository authorRepository;

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
        authorRepository.save(author);
    }

    @Override
    public List<Author> getAllAuthors() {
        // TODO Auto-generated method stub
        return authorRepository.findAll();
    }

    @Override
    public void deleteAuthor(Long id) {
        Optional<Author> authorOptional = authorRepository.findById(id);

        if (authorOptional.isPresent()) {
            Author author = authorOptional.get();
            authorRepository.delete(author);
        } else {
            // Manejar el caso cuando el autor no existe
            throw new EntityNotFoundException("Author with id " + id + " not found");
        }
    }
}
