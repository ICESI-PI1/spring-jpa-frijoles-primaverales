package com.library.Library.persistence.models.dto;

import com.library.Library.persistence.models.Book;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-11-20T11:47:50-0500",
    comments = "version: 1.4.2.Final, compiler: Eclipse JDT (IDE) 3.35.0.v20230814-2020, environment: Java 17.0.8.1 (Eclipse Adoptium)"
)
public class BookMapperImpl implements BookMapper {

    @Override
    public BookDTO bookToBookDTO(Book book) {
        if ( book == null ) {
            return null;
        }

        BookDTO bookDTO = new BookDTO();

        bookDTO.setAuthor( book.getAuthor() );
        bookDTO.setId( book.getId() );
        bookDTO.setTitle( book.getTitle() );

        return bookDTO;
    }
}
