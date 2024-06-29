package com.example.Docker;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

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
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest
public class TestDockerControllerMethods {



        @Autowired
        private MockMvc mockMvc;

        @MockBean
        private UserRepository userRepository;

        @InjectMocks
        private DockerController dockerController;

        private List<User> userList;

        @BeforeEach
        void setUp() {
            MockitoAnnotations.openMocks(this);

            User user1 = new User(1, "Adam", 15);
            User user2 = new User(2, "George", 23);

            userList = Arrays.asList(user1, user2);
        }

        @Test
        void testGetAllUsers() throws Exception {
            when(userRepository.findAll()).thenReturn(userList);

            mockMvc.perform(get("/allUsers"))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.length()").value(userList.size()))
                    .andExpect(jsonPath("$[0].id").value(userList.get(0).getId()))
                    .andExpect(jsonPath("$[0].name").value(userList.get(0).getName()))
                    .andExpect(jsonPath("$[0].age").value(userList.get(0).getAge()))
                    .andExpect(jsonPath("$[1].id").value(userList.get(1).getId()))
                    .andExpect(jsonPath("$[1].name").value(userList.get(1).getName()))
                    .andExpect(jsonPath("$[1].age").value(userList.get(1).getAge()));
        }
    }

