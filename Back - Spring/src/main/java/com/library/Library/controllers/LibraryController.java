package com.library.Library.controllers;

import com.library.Library.persistence.models.Author;
import com.library.Library.persistence.models.Book;
import com.library.Library.services.Impl.AuthorServiceImpl;
import com.library.Library.services.Impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/")
public class LibraryController {

    @Autowired
    private BookServiceImpl bookService;

    @Autowired
    private AuthorServiceImpl authorService;

    @GetMapping(path = "books")
    public ArrayList<Book> getAllBooks(){
        return (ArrayList<Book>) bookService.getAllBooks();
    }

    @PostMapping(path = "books")
    public String createBook( @RequestBody Book newBook){

        if(newBook.getAuthor() != null){
            Optional<Author> auxAuthor = authorService.findById(newBook.getAuthor().getId());
            if(auxAuthor.isPresent()) {
                newBook.setAuthor(auxAuthor.get());
                bookService.save(newBook);
                return "Book created";
            }
        }


        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Author does not exist"
        );

    }

    @GetMapping(path = "books/{id}")
    public Book getBookById(@PathVariable("id") Long id){
        if(bookService.findById(id).isPresent()) {
            return bookService.findById(id).get();
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "entity not found"
        );
    }

    @PutMapping(path = "books/{id}")
    public String updateBookById(@RequestBody Book newBook, @PathVariable("id") Long id){
        if(newBook.getAuthor() != null) {
            Optional<Author> auxAuthor = authorService.findById(newBook.getAuthor().getId());
            if (bookService.findById(id).isPresent() && auxAuthor.isPresent()) {
                Book book = bookService.findById(id).get();
                book.setAuthor(auxAuthor.get());
                book.setTitle(newBook.getTitle());

                bookService.save(book);

                return "Book modified";
            }
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "entity not found"
        );
    }

    @DeleteMapping(path = "books/{id}")
    public String deleteBookById(@PathVariable("id") Long id){
        if(bookService.findById(id).isPresent()) {
            bookService.deleteBook(id);
            return "Book deleted";
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "entity not found"
        );
    }

    //--------------Author methods ----------------
    @GetMapping(path = "authors")
    public ArrayList<Author> getAllAuthors(){
        return (ArrayList<Author>) authorService.getAllAuthors();
    }

    @PostMapping(path = "authors")
    public String createAuthor( @RequestBody Author newAuthor){
        authorService.save(newAuthor);
        return "Author created";
    }

    @GetMapping(path = "authors/{id}")
    public Author getAuthorById(@PathVariable("id") Long id){
        if(authorService.findById(id).isPresent()) {
            return authorService.findById(id).get();
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "entity not found"
        );
    }

    @PutMapping(path = "authors/{id}")
    public String updateAuthorById(@RequestBody Author newAuthor, @PathVariable("id") Long id){
        if(authorService.findById(id).isPresent()) {
            Author author = authorService.findById(id).get();
            author.setName(newAuthor.getName());
            author.setNationality(newAuthor.getNationality());

            this.authorService.save(author);
            return "Author modified";
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "entity not found"
        );
    }

    @DeleteMapping(path = "authors/{id}")
    public String deleteAuthorById(@PathVariable("id") Long id){
        if(authorService.findById(id).isPresent()) {
            authorService.deleteAuthor(id);
            return "Author deleted";
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "entity not found"
        );
    }

    @GetMapping(path = "authors/{id}/books")
    public ArrayList<Book> getAuthorBooks(@PathVariable("id") Long id){
        return (ArrayList<Book>) bookService.getAuthorBooks(id);
    }
}
