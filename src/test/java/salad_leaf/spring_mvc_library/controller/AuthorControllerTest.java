package salad_leaf.spring_mvc_library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import salad_leaf.spring_mvc_library.model.Author;
import salad_leaf.spring_mvc_library.service.AuthorService;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthorController.class)
public class AuthorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthorService authorService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldCreateAuthor() throws Exception {
        Author author = new Author();
        author.setId(UUID.randomUUID());
        author.setName("Толстой");
        author.setBirthYear(1828);
        author.setDeathYear(1910);

        when(authorService.createAuthor(any(Author.class))).thenReturn(author);

        mockMvc.perform(post("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(author)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Толстой"));
    }

    @Test
    void shouldGetAuthorById() throws Exception {
        UUID id = UUID.randomUUID();
        Author author = new Author();
        author.setId(id);
        author.setName("Толстой");
        author.setBirthYear(1828);
        author.setDeathYear(1910);

        when(authorService.findAuthorById(id)).thenReturn(author);

        mockMvc.perform(get("/authors/" + id))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Толстой"));
    }
}
