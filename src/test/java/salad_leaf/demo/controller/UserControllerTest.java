package salad_leaf.demo.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.server.ResponseStatusException;
import salad_leaf.demo.model.User;
import salad_leaf.demo.service.UserService;

import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService; // вот так нужно!

    @Test
    void shouldReturnUsersList() throws Exception {
        User mockUser = new User("Lenin Grib", "gribnik@lol.com", List.of());
        Mockito.when(userService.getAllUsers()).thenReturn(List.of(mockUser));

        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Lenin Grib"))
                .andExpect(jsonPath("$[0].email").value("gribnik@lol.com"))
                .andExpect(jsonPath("$[0].orderList").doesNotExist());
    }

    @Test
    void shouldReturnUserDetails() throws Exception {
        UUID userId = UUID.randomUUID();
        User mockUser = new User("Lenin Crab", "bochkom@lol.com", List.of());

        Mockito.when(userService.findUserById(userId)).thenReturn(mockUser);

        mockMvc.perform(get("/users/" + userId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Lenin Crab"))
                .andExpect(jsonPath("$.email").value("bochkom@lol.com"))
                .andExpect(jsonPath("$.orderList").isArray())
                .andExpect(jsonPath("$.orderList.length()").value(0));
    }

    @Test
    void shouldNotFoundUser() throws Exception {
        Mockito.when(userService.findUserById(Mockito.any())).thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));
        mockMvc.perform(get("/users/" + UUID.randomUUID()))
                .andExpect(status().isNotFound());

    }
}
