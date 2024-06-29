package com.example.Docker;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(DockerController.class)
public class TestDockerControllerMethods {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @InjectMocks
    private DockerController dockerController;

    private List<User> userList;
    private User user1;
    private User user2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        user1 = new User(1, "John", 30);
        user2 = new User(2, "Jane", 25);

        userList = Arrays.asList(user1, user2);
    }

    @Test
    void testGetAllUsers() throws Exception {
        when(userRepository.findAll()).thenReturn(userList);

        mockMvc.perform(get("/allUsers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(userList.size()))
                .andExpect(jsonPath("$[0].id").value(user1.getId()))
                .andExpect(jsonPath("$[0].name").value(user1.getName()))
                .andExpect(jsonPath("$[0].age").value(user1.getAge()))
                .andExpect(jsonPath("$[1].id").value(user2.getId()))
                .andExpect(jsonPath("$[1].name").value(user2.getName()))
                .andExpect(jsonPath("$[1].age").value(user2.getAge()));
    }

    @Test
    void testPostUser() throws Exception {
        User newUser = new User(3, "Alice", 28);
        when(userRepository.save(any(User.class))).thenReturn(newUser);

        mockMvc.perform(post("/postUser")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(newUser)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(newUser.getId()))
                .andExpect(jsonPath("$.name").value(newUser.getName()))
                .andExpect(jsonPath("$.age").value(newUser.getAge()));
    }



    @Test
    void testGetAllUsers_noUsers() throws Exception {
        when(userRepository.findAll()).thenReturn(Arrays.asList());

        mockMvc.perform(get("/allUsers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));
    }

    @Test
    void testTests() throws Exception {
        mockMvc.perform(get("/test"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("test"));
    }


}
