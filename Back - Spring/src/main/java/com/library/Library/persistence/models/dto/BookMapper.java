package com.library.Library.persistence.models.dto;


import com.library.Library.persistence.models.Book;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BookMapper {

    BookMapper INSTANCE = Mappers.getMapper(BookMapper.class);

    BookDTO bookToBookDTO(Book book);

}