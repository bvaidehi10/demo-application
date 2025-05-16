package com.demo.controller;

import com.demo.dto.ApplicationDto;
import com.demo.repository.ApplicationRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ApplicationRepository applicationRepository;

    @Test
    void testRegisterApplication_success() throws Exception {
        ApplicationDto applicationDto = ApplicationDto.builder()
                .name("My App")
                .objectID("obj123")
                .applicationID("app456")
                .platform("Web")
                .accountType("Single Tenant")
                .redirectUrl("https://example.com")
                .build();

        mockMvc.perform(post("/api/application/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(applicationDto)))
                .andExpect(status().isCreated());
    }
}
