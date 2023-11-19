import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.Library.controllers.LibraryController;
import com.library.Library.persistence.models.Author;
import com.library.Library.persistence.models.Book;
import com.library.Library.persistence.models.dto.BookDTO;
import com.library.Library.services.Impl.AuthorServiceImpl;
import com.library.Library.services.Impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LibraryApplicationTest {

    @Mock
    private BookServiceImpl bookService;

    @Mock
    private AuthorServiceImpl authorService;

    @InjectMocks
    private LibraryController libraryController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllBooks() {
        // Define el comportamiento esperado del servicio mock
        List<Book> mockBooks = new ArrayList<>();
        when(bookService.getAllBooks()).thenReturn(mockBooks);

        // Llama al método del controlador y verifica el resultado
        List<BookDTO> result = libraryController.getAllBooks();
        assertNotNull(result);
        assertEquals(mockBooks.size(), result.size());

    }

    @Test
    void createBook() {
        // Datos de prueba
        BookDTO newBookDTO = new BookDTO(1L, "Book Title", new Author(1L, "Author Name", "Author Nationality"));
        Author auxAuthor = new Author(1L, "Author Name", "Author Nationality");

        // Define el comportamiento esperado del servicio mock
        when(authorService.findById(newBookDTO.getAuthor().getId())).thenReturn(Optional.of(auxAuthor));

        // Llama al método del controlador y verifica el resultado
        String result = libraryController.createBook(newBookDTO);
        assertEquals("Book created", result);

    }

    @Test
    void createBookAuthorNotFound() {
        // Datos de prueba con autor no encontrado
        BookDTO newBookDTO = new BookDTO(1L, "Book Title", new Author(1L, "Author Name", "Author Nationality"));

        // Define el comportamiento esperado del servicio mock
        when(authorService.findById(newBookDTO.getAuthor().getId())).thenReturn(Optional.empty());

        // Llama al método del controlador y verifica que se lance la excepción esperada
        assertThrows(ResponseStatusException.class, () -> {
            libraryController.createBook(newBookDTO);
        });

       verify(bookService, never()).save(any(Book.class));
    }

    @Test
    void getBookById() {
        // Datos de prueba
        Long bookId = 1L;
        Book mockBook = new Book(bookId, "Book Title", new Date(),new Author(1L, "Author Name", "Author Nationality"));

        // Define el comportamiento esperado del servicio mock
        when(bookService.findById(bookId)).thenReturn(Optional.of(mockBook));

        // Llama al método del controlador y verifica el resultado
        BookDTO result = libraryController.getBookById(bookId);
        assertNotNull(result);
        assertEquals(mockBook.getId(), result.getId());

    }

    @Test
    void getBookByIdNotFound() {
        // Datos de prueba con libro no encontrado
        Long bookId = 1L;

        // Define el comportamiento esperado del servicio mock
        when(bookService.findById(bookId)).thenReturn(Optional.empty());

        // Llama al método del controlador y verifica que se lance la excepción esperada
        assertThrows(ResponseStatusException.class, () -> {
            libraryController.getBookById(bookId);
        });


    }

    @Test
    void updateBookById() {
        // Datos de prueba
        Long bookId = 1L;
        BookDTO newBookDTO = new BookDTO(bookId, "Updated Book Title", new Author(1L, "Author Name", "Author Nationality"));
        Author auxAuthor = new Author(1L, "Author Name", "Author Nationality");
        Book mockBook = new Book(bookId, "Original Book Title", new Date(), auxAuthor);

        // Define el comportamiento esperado del servicio mock
        when(authorService.findById(newBookDTO.getAuthor().getId())).thenReturn(Optional.of(auxAuthor));
        when(bookService.findById(bookId)).thenReturn(Optional.of(mockBook));

        // Llama al método del controlador y verifica el resultado
        String result = libraryController.updateBookById(newBookDTO, bookId);
        assertEquals("Book modified", result);

    }

    @Test
    void updateBookByIdNotFound() {
        // Datos de prueba con libro o autor no encontrado
        Long bookId = 1L;
        BookDTO newBookDTO = new BookDTO(bookId, "Updated Book Title", new Author(1L, "Author Name", "Author Nationality"));

        // Define el comportamiento esperado del servicio mock
        when(authorService.findById(newBookDTO.getAuthor().getId())).thenReturn(Optional.empty());
        when(bookService.findById(bookId)).thenReturn(Optional.empty());

        // Llama al método del controlador y verifica que se lance la excepción esperada
        assertThrows(ResponseStatusException.class, () -> {
            libraryController.updateBookById(newBookDTO, bookId);
        });

        verify(bookService, never()).save(any(Book.class));
    }

    @Test
    void deleteBookById() {
        // Datos de prueba
        Long bookId = 1L;
        Book mockBook = new Book(bookId, "Book Title", new Date(), new Author(1L, "Author Name", "Author Nationality"));

        // Define el comportamiento esperado del servicio mock
        when(bookService.findById(bookId)).thenReturn(Optional.of(mockBook));

        // Llama al método del controlador y verifica el resultado
        String result = libraryController.deleteBookById(bookId);
        assertEquals("Book deleted", result);

    }

    @Test
    void deleteBookByIdNotFound() {
        // Datos de prueba con libro no encontrado
        Long bookId = 1L;

        // Define el comportamiento esperado del servicio mock
        when(bookService.findById(bookId)).thenReturn(Optional.empty());

        // Llama al método del controlador y verifica que se lance la excepción esperada
        assertThrows(ResponseStatusException.class, () -> {
            libraryController.deleteBookById(bookId);
        });

        // Verifica que el servicio se haya llamado una vez
        verify(bookService, never()).deleteBook(bookId);
    }


    @Test
    void getAllAuthors() {
        // Define el comportamiento esperado del servicio mock
        List<Author> mockAuthors = new ArrayList<>();
        when(authorService.getAllAuthors()).thenReturn(mockAuthors);

        // Llama al método del controlador y verifica el resultado
        ArrayList<Author> result = libraryController.getAllAuthors();
        assertNotNull(result);
        assertEquals(mockAuthors.size(), result.size());

    }

    @Test
    void createAuthor() {
        // Datos de prueba
        Author newAuthor = new Author(1L, "New Author", "Country");

        // Llama al método del controlador y verifica el resultado
        String result = libraryController.createAuthor(newAuthor);
        assertEquals("Author created", result);

    }

    @Test
    void getAuthorById() {
        // Datos de prueba
        Long authorId = 1L;
        Author mockAuthor = new Author(authorId, "Author Name", "Author Nationality");

        // Define el comportamiento esperado del servicio mock
        when(authorService.findById(authorId)).thenReturn(Optional.of(mockAuthor));

        // Llama al método del controlador y verifica el resultado
        Author result = libraryController.getAuthorById(authorId);
        assertNotNull(result);
        assertEquals(mockAuthor.getId(), result.getId());

    }

    @Test
    void getAuthorByIdNotFound() {
        // Datos de prueba con autor no encontrado
        Long authorId = 1L;

        // Define el comportamiento esperado del servicio mock
        when(authorService.findById(authorId)).thenReturn(Optional.empty());

        // Llama al método del controlador y verifica que se lance la excepción esperada
        assertThrows(ResponseStatusException.class, () -> {
            libraryController.getAuthorById(authorId);
        });

    }

    @Test
    void updateAuthorById() {
        // Datos de prueba
        Author newAuthor = new Author(1L, "Updated Author Name", "Updated Country");
        Author mockAuthor = new Author(1L, "Original Author Name", "Original Country");

        // Define el comportamiento esperado del servicio mock
        when(authorService.findById(1L)).thenReturn(Optional.of(mockAuthor));

        // Llama al método del controlador y verifica el resultado
        String result = libraryController.updateAuthorById(newAuthor, 1L);
        assertEquals("Author modified", result);

    }

    @Test
    void updateAuthorByIdNotFound() {
        // Datos de prueba con autor no encontrado
        Long authorId = 1L;
        Author newAuthor = new Author(authorId, "Updated Author Name", "Updated Country");

        // Define el comportamiento esperado del servicio mock
        when(authorService.findById(authorId)).thenReturn(Optional.empty());

        // Llama al método del controlador y verifica que se lance la excepción esperada
        assertThrows(ResponseStatusException.class, () -> {
            libraryController.updateAuthorById(newAuthor, authorId);
        });

        verify(authorService, never()).save(any(Author.class));
    }

    @Test
    void deleteAuthorById() {
        // Datos de prueba
        Long authorId = 1L;

        // Define el comportamiento esperado del servicio mock
        when(authorService.findById(authorId)).thenReturn(Optional.of(new Author()));

        // Llama al método del controlador y verifica el resultado
        String result = libraryController.deleteAuthorById(authorId);
        assertEquals("Author deleted", result);

    }

    @Test
    void deleteAuthorByIdNotFound() {
        // Datos de prueba con autor no encontrado
        Long authorId = 1L;

        // Define el comportamiento esperado del servicio mock
        when(authorService.findById(authorId)).thenReturn(Optional.empty());

        // Llama al método del controlador y verifica que se lance la excepción esperada
        assertThrows(ResponseStatusException.class, () -> {
            libraryController.deleteAuthorById(authorId);
        });

        // Verifica que el servicio se haya llamado una vez
        verify(authorService, times(1)).findById(authorId);
        verify(bookService, never()).getAuthorBooks(authorId);
        verify(bookService, never()).deleteBook(anyLong());
        verify(authorService, never()).deleteAuthor(authorId);
    }

    @Test
    void getAuthorBooks() {
        // Datos de prueba
        Long authorId = 1L;
        List<Book> mockBooks = new ArrayList<>();
        mockBooks.add(new Book(1L, "Book 1", new Date(), new Author(1L, "Author", "Country")));
        mockBooks.add(new Book(2L, "Book 2", new Date(), new Author(1L, "Author", "Country")));

        // Define el comportamiento esperado del servicio mock
        when(bookService.getAuthorBooks(authorId)).thenReturn(mockBooks);

        // Llama al método del controlador y verifica el resultado
        List<BookDTO> result = libraryController.getAuthorBooks(authorId);
        assertNotNull(result);
        assertEquals(mockBooks.size(), result.size());

    }

}
