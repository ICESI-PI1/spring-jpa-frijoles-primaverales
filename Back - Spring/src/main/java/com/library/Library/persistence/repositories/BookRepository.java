package com.library.Library.persistence.repositories;

import java.util.List;
import com.library.Library.persistence.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAuthorId(Long authorId);
}
