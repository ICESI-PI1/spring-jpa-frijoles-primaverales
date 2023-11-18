package com.library.Library.persistence.repositories.Impl;

import com.library.Library.LibraryApplication;
import com.library.Library.persistence.models.Author;
import com.library.Library.persistence.models.Book;
import com.library.Library.persistence.repositories.IAuthorRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class AuthorRepositoryImpl implements IAuthorRepository {


    private Logger log = LoggerFactory.getLogger(LibraryApplication.class);

    private List<Author> authors = new ArrayList<>();

    public AuthorRepositoryImpl(){
        super();
    }

    @Override
    public Optional<Author> findById(Long id){
        Optional<Author> match = authors.stream().filter(p-> Objects.equals(p.getId(), id)).findFirst();
        if(match.isPresent()){
            log.info("Object with id: "+id+" found: " + match.toString());
        }else{
            log.info("Object with id: "+id+" not found");
        }
        return match;

    }

    @Override
    public Author save(Author author) {
        Author existingAuthor = findById(author.getId()).orElse(null);
        if (existingAuthor == null){
            authors.add(author);
        }else {
            authors.remove(existingAuthor);
            Author newAuthor = new Author(author);
            authors.add(newAuthor);
        }
        log.info("Saving author" + author.toString());
        return author;
    }

    @Override
    public void add(Author author) {
        Author existingAuthor = findById(author.getId()).orElse(null);
        if (existingAuthor == null){
            authors.add(author);
        }else{
            authors.remove(existingAuthor);
            Author newAuthor = new Author(author);
            authors.add(newAuthor);
        }
        log.info("Saving author: " + author.toString());
    }

    @Override
    public List<Author> getAllAuthors() {
        log.info("List of authors: " + this.authors);
        return this.authors;
    }

    @Override
    public void deleteAuthor(Long id) {
        int len = authors.size();
        authors.removeIf(author -> Objects.equals(author.getId(), id));

        if(len > authors.size()){
            log.info("Author deleted");
        }else{
            log.info("Error, Author not found");
        }
    }
/*
    @Override
    public void addBook(Long id, Book book) {
        Author existingAuthor = findById(id).orElse(null);
        if(existingAuthor!=null){
            log.info("Book : "+book.toString()+ " added to Author: "+ existingAuthor.toString());
        }else{
            log.info("Author not found");
        }
    }

    @Override
    public String getBooks(Long id) {
        Author existingAuthor = findById(id).orElse(null);
        if(existingAuthor!=null){
            ArrayList<Book> books= existingAuthor.getBooks();
            String ans=existingAuthor.getName()+" books:\n";
            if(books.size()==0){
                ans+="Books empty.";
            }else{
                for(int i=0;i<books.size();i++){
                    ans+="* "+books.get(i).toString()+"\n";
                }
            }
            return ans;
        }else{
            return "Author not found";
        }
    }

 */
}
