package com.library.Library.persistence.models.dto;

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

    public BookDTO(Book book) {
        this(book.getId(), book.getTitle(), book.getAuthor());
    }
}