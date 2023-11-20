package com.library.Library.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.library.Library.persistence.models.Author;
import com.library.Library.persistence.models.Book;
import com.library.Library.persistence.repositories.AuthorRepository;
import com.library.Library.persistence.repositories.BookRepository;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class DataInitializer {
    
    @Autowired
    private AuthorRepository authorRepository;

    @Autowired
    private BookRepository bookRepository;

    @PostConstruct
    public void init() {
        // Crear autores de prueba
        Author author1 = new Author();
        author1.setName("J.K. Rowling");
        author1.setNationality("British");
        authorRepository.save(author1);

        Author author2 = new Author();
        author2.setName("George R.R. Martin");
        author2.setNationality("American");
        authorRepository.save(author2);
        
        Author author3 = new Author();
        author3.setName("J.R.R. Tolkien");
        author3.setNationality("British");
        authorRepository.save(author3);

        Author author4 = new Author();
        author4.setName("Agatha Christie");
        author4.setNationality("British");
        authorRepository.save(author4);

        // Crear libros de prueba
        Book book1 = new Book();
        book1.setTitle("Harry Potter and the Sorcerer's Stone");
        book1.setPublicationDate(parseDate("1997-06-26"));
        book1.setAuthor(author1);
        bookRepository.save(book1);

        Book book2 = new Book();
        book2.setTitle("A Game of Thrones");
        book2.setPublicationDate(parseDate("1996-08-06"));
        book2.setAuthor(author2);

        Book book3 = new Book();
        book3.setTitle("The Hobbit");
        book3.setPublicationDate(parseDate("1937-09-21"));
        book3.setAuthor(author3);
        bookRepository.save(book3);

        Book book4 = new Book();
        book4.setTitle("Murder on the Orient Express");
        book4.setPublicationDate(parseDate("1934-01-01"));
        book4.setAuthor(author4);
        bookRepository.save(book4);

        Book book5 = new Book();
        book5.setTitle("Harry Potter and the Chamber of Secrets");
        book5.setPublicationDate(parseDate("1998-07-02"));
        book5.setAuthor(author1);
        bookRepository.save(book5);

        Book book6 = new Book();
        book6.setTitle("A Clash of Kings");
        book6.setPublicationDate(parseDate("1998-11-16"));
        book6.setAuthor(author2);
        bookRepository.save(book6);

        System.out.println("********************************MOSTRANDO LIBRO*******************");
        System.out.println(book6);
        
    }

    private Date parseDate(String dateStr) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        } catch (ParseException e) {
            throw new RuntimeException("Error parsing date: " + dateStr, e);
        }
    }
}
