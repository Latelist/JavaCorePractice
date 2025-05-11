//package salad_leaf.spring_mvc_library.controller;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import salad_leaf.spring_mvc_library.model.Author;
//import salad_leaf.spring_mvc_library.model.Book;
//import salad_leaf.spring_mvc_library.service.BookService;
//
//import java.util.UUID;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
//
//@WebMvcTest(BookController.class)
//public class BookControllerTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @MockBean
//    private BookService bookService;
//
//    @Autowired
//    private ObjectMapper objectMapper;
//
//    @Test
//    void shouldCreateBookForAuthor() throws Exception {
//        UUID authorId = UUID.randomUUID();
//        Author author = new Author();
//        author.setId(authorId);
//        author.setName("Толстой");
//
//        Book book = new Book();
//        book.setId(1L);
//        book.setTitle("Анна Каренина");
//        book.setPublicationYear(1877);
//        book.setAuthor(author);
//
//        when(bookService.createBook(any(UUID.class), any(Book.class))).thenReturn(book);
//
//        mockMvc.perform(post("/books/author/" + authorId)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(book)))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.title").value("Анна Каренина"))
//                .andExpect(jsonPath("$.publicationYear").value(1877));
//    }
//
//    @Test
//    void shouldFindBookById() throws Exception {
//        Book book = new Book();
//        book.setId(1L);
//        book.setTitle("Анна Каренина");
//        book.setPublicationYear(1877);
//
//        when(bookService.findBookById(1L)).thenReturn(book);
//
//        mockMvc.perform(get("/books/1"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(jsonPath("$.title").value("Анна Каренина"));
//    }
//}
