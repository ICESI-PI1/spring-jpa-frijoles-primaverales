import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.Library.controllers.LibraryController;
import com.library.Library.persistence.models.Author;
import com.library.Library.persistence.models.Book;
import com.library.Library.services.Impl.AuthorServiceImpl;
import com.library.Library.services.Impl.BookServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LibraryApplicationTest {

    @InjectMocks
    private LibraryController libraryController;

    @Mock
    private BookServiceImpl bookService;

    @Mock
    private AuthorServiceImpl authorService;

    @Test
    public void testCreateBookSuccess() {
        // Arrange
        Author author = new Author();
        author.setId(1L);

        Book newBook = new Book();
        newBook.setTitle("Test Book");
        newBook.setAuthor(author);

        when(authorService.findById(1L)).thenReturn(Optional.of(author));
        when(bookService.save(newBook)).thenReturn(newBook);

        // Act
        String result = libraryController.createBook(newBook);

        // Assert
        assertEquals("Book created", result);
        verify(authorService, times(1)).findById(1L);
        verify(bookService, times(1)).save(newBook);
    }

    @Test
    public void testCreateBookAuthorNotFound() {
        // Arrange
        Book newBook = new Book();
        newBook.setTitle("Test Book");
        newBook.setAuthor(new Author());  // Author without ID

        when(authorService.findById(anyLong())).thenReturn(Optional.empty());

        // Act & Assert
        ResponseStatusException exception = Assertions.assertThrows(ResponseStatusException.class, () -> {
            libraryController.createBook(newBook);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("Author does not exist", exception.getReason());
        verify(authorService, times(1)).findById(anyLong());
        verify(bookService, never()).save(any());
    }
}
