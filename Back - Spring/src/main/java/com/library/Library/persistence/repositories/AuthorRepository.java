package com.library.Library.persistence.repositories;

import com.library.Library.persistence.models.Author;
import com.library.Library.persistence.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

}
