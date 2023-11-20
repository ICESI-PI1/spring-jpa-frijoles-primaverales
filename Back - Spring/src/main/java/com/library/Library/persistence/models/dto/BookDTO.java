package com.library.Library.persistence.models.dto;

import java.util.Date;

import com.library.Library.persistence.models.Author;
import com.library.Library.persistence.models.Book;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {

    private Long id;
    private String title;
    private Author author;
    private Date publicationDate;

    public BookDTO(Book book) {
        this(book.getId(), book.getTitle(), book.getAuthor(), book.getPublicationDate());
    }

    public BookDTO(Long id, String title, Date date) {
        this(id, title, null, date);
    }

    public BookDTO(Long id, String title, Author author){
        this(id, title, author,null);
    }


}