package salad_leaf.jdbc_practice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import salad_leaf.jdbc_practice.controller.BookController;
import salad_leaf.jdbc_practice.entities.Book;
import salad_leaf.jdbc_practice.service.BookService;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookService bookService;

    @Autowired
    private ObjectMapper objectMapper;

    private final UUID bookId = UUID.randomUUID();

    private final Book testBook = new Book(
            bookId,
            "Test Title",
            "Test Author",
            2020
    );

    @Test
    void shouldReturnBookById() throws Exception {
        Mockito.when(bookService.findById(bookId)).thenReturn(testBook);

        mockMvc.perform(get("/books/" + bookId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Title"))
                .andExpect(jsonPath("$.author").value("Test Author"))
                .andExpect(jsonPath("$.publicationYear").value(2020));
    }

    @Test
    void shouldReturnAllBooks() throws Exception {
        Mockito.when(bookService.findAll()).thenReturn(List.of(testBook));

        mockMvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title").value("Test Title"));
    }

    @Test
    void shouldCreateBook() throws Exception {
        Mockito.when(bookService.save(any(Book.class))).thenReturn(testBook);

        mockMvc.perform(post("/books")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testBook)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value("Test Title"));
    }

    @Test
    void shouldUpdateBook() throws Exception {
        Mockito.when(bookService.findById(bookId)).thenReturn(testBook);
        Mockito.when(bookService.save(any(Book.class))).thenReturn(testBook);

        mockMvc.perform(put("/books/" + bookId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(testBook)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.author").value("Test Author"));
    }

    @Test
    void shouldDeleteBook() throws Exception {
        mockMvc.perform(delete("/books/" + bookId))
                .andExpect(status().isOk());
        Mockito.verify(bookService).deleteById(eq(bookId));
    }
}
