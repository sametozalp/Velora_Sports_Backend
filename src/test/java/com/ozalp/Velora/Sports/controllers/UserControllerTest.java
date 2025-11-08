package com.ozalp.Velora.Sports.controllers;

import com.ozalp.Velora.Sports.business.abstracts.UserService;
import com.ozalp.Velora.Sports.controllers.api.UserController;
import com.ozalp.Velora.Sports.entities.concretes.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private UserService userService;


    @Test
    public void create() throws Exception {
        User newUser = new User();
        newUser.setFirstName("samet");
        newUser.setLastName("ozalp");
        newUser.setPassword("123456");
        newUser.setEmail("sametozalpbusiness@gmail.com");
        newUser.setUsername("sametozalp");

        User savedUser = new User();
        savedUser.setFirstName("samet");
        savedUser.setLastName("ozalp");
        savedUser.setPassword("123456");
        savedUser.setEmail("sametozalpbusiness@gmail.com");
        savedUser.setUsername("sametozalp");

        when(userService.create(any(User.class))).thenReturn(savedUser);

        mockMvc.perform(post("/api/user/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value("{\n" +
                        "  \"id\": \"90d2722e-8ac8-42e3-9ead-86bc2483f25f\",\n" +
                        "  \"firstName\": \"samet\",\n" +
                        "  \"lastName\": \"ozalp\",\n" +
                        "  \"email\": \"sametozalpbusiness@gmail.com\",\n" +
                        "  \"accessToken\": null,\n" +
                        "  \"refreshToken\": null\n" +
                        "}"))
                .andExpect(jsonPath("$.header").value("Location"));
    }
}
