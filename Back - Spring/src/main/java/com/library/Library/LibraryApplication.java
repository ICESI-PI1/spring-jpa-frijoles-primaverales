package com.library.Library;

import com.library.Library.persistence.models.Book;
import com.library.Library.security.WebSecurityConfig;
import com.library.Library.services.IBookService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication(scanBasePackages = "com.library.Library")
public class LibraryApplication {

  public static void main(String[] args) {

    SpringApplication.run(LibraryApplication.class, args);

  }
}
