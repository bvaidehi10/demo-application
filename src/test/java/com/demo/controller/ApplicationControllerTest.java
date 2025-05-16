package com.demo.controller;

import com.demo.service.ApplicationService;
import com.demo.dto.ApplicationDto;
import com.demo.exceptions.ResourceNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;



@SpringBootTest
@AutoConfigureMockMvc
public class ApplicationControllerTest {

    private final String REGISTER_API = "/api/application/register";
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ApplicationService applicationService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void testRegisterApplication_success() throws Exception {
        ApplicationDto applicationDto = getValidDto();
        when(applicationService.registerApplication(applicationDto)).thenReturn(applicationDto);
        mockMvc.perform(post(REGISTER_API)
                .content(objectMapper.writeValueAsString(applicationDto))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isCreated());

    }

    @Test
    void testRegisterApplication_BadRequest() throws Exception {
        ApplicationDto applicationDto = getValidDto();
        applicationDto.setName("");
        when(applicationService.registerApplication(applicationDto)).thenReturn(applicationDto);
        mockMvc.perform(post(REGISTER_API)
                        .content(objectMapper.writeValueAsString(applicationDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

    }

    @Test
    void testRegisterApplication_platform_notFound() throws Exception {
        ApplicationDto applicationDto = getValidDto();
        applicationDto.setPlatform("Webb");
        when(applicationService.registerApplication(applicationDto)).thenThrow(new ResourceNotFoundException("Platform is not found"));
        mockMvc.perform(post(REGISTER_API)
                        .content(objectMapper.writeValueAsString(applicationDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    @Test
    void testRegisterApplication_accountType_notFound() throws Exception {
        ApplicationDto applicationDto = getValidDto();
        applicationDto.setAccountType("Webb");
        when(applicationService.registerApplication(applicationDto)).thenThrow(new ResourceNotFoundException("Account Type is not found"));
        mockMvc.perform(post(REGISTER_API)
                        .content(objectMapper.writeValueAsString(applicationDto))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    private ApplicationDto getValidDto() {
        return  ApplicationDto.builder()
                .name("My App")
                .objectID("obj123")
                .applicationID("app456")
                .platform("Web")
                .accountType("Single Tenant")
                .redirectUrl("https://example.com")
                .build();
    }

}
