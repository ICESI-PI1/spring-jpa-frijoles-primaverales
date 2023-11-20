package com.library.Library.controllers;

import com.library.Library.persistence.models.Author;
import com.library.Library.persistence.models.Book;
import com.library.Library.persistence.models.dto.BookDTO;
import com.library.Library.services.Impl.AuthorServiceImpl;
import com.library.Library.services.Impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

@RestController
@RequestMapping("/")
@Validated
public class LibraryController {

    @Autowired
    private BookServiceImpl bookService;

    @Autowired
    private AuthorServiceImpl authorService;

    @GetMapping(path = "books")
    public List<BookDTO> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return books.stream()
                .map(book -> new BookDTO(book.getId(), book.getTitle(), new Author(book.getAuthor())))
                .collect(Collectors.toList());
    }

    @PostMapping(path = "books")
    public String createBook(@RequestBody BookDTO newBookDTO){
        if(newBookDTO.getAuthor() != null){
            Optional<Author> auxAuthor = authorService.findById(newBookDTO.getAuthor().getId());
            if(auxAuthor.isPresent()) {
                Book newBook = new Book();
                newBook.setId(newBookDTO.getId());
                newBook.setTitle(newBookDTO.getTitle());
                newBook.setAuthor(auxAuthor.get());
                newBook.setPublicationDate(new Date());

                bookService.save(newBook);
                return "Book created";
            }
        }

        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Author does not exist"
        );
    }

    @GetMapping(path = "books/{id}")
    public BookDTO getBookById(@PathVariable("id") Long id){
        if(bookService.findById(id).isPresent()) {
            Book book = bookService.findById(id).get();
            return new BookDTO(book.getId(), book.getTitle(), new Author(book.getAuthor()));
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "entity not found"
        );
    }

    @PutMapping(path = "books/{id}")
    public String updateBookById(@RequestBody BookDTO newBookDTO, @PathVariable("id") Long id){
        if(newBookDTO.getAuthor() != null) {
            Optional<Author> auxAuthor = authorService.findById(newBookDTO.getAuthor().getId());
            if (bookService.findById(id).isPresent() && auxAuthor.isPresent()) {
                Book book = bookService.findById(id).get();
                book.setAuthor(auxAuthor.get());
                book.setTitle(newBookDTO.getTitle());

                bookService.save(book);

                return "Book modified";
            }
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "entity not found"
        );
    }

    @DeleteMapping(path = "books/{id}")
    public String deleteBookById(@PathVariable("id") Long id) {
        if (bookService.findById(id).isPresent()) {
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
    public String createAuthor(@Valid @RequestBody Author newAuthor){
        if(!newAuthor.getName().equals("") && !newAuthor.getNationality().equals("")){
            authorService.save(newAuthor);
            return "Author created";
        }
        throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST, "Fields 'name' and 'nationality' cannot be null"
        );
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
            List<Book> authorBooks = bookService.getAuthorBooks(id);
            if (!authorBooks.isEmpty()){
                for (Book b: authorBooks) {
                    bookService.deleteBook(b.getId());
                }
                authorService.deleteAuthor(id);
            }else{
                authorService.deleteAuthor(id);
            }

            return "Author deleted";
        }
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "entity not found"
        );
    }

    @GetMapping(path = "authors/{id}/books")
    public List<BookDTO> getAuthorBooks(@PathVariable("id") Long id) {
        List<Book> authorBooks = bookService.getAuthorBooks(id);
        return authorBooks.stream()
                .map(book -> new BookDTO(book.getId(), book.getTitle(), new Author(book.getAuthor())))
                .collect(Collectors.toList());
    }
}
