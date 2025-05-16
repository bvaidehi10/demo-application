package com.demo.service;

import com.demo.repository.AccountTypeRepository;
import com.demo.repository.entity.Platform;
import com.demo.dto.ApplicationDto;
import com.demo.exceptions.ResourceNotFoundException;
import com.demo.repository.ApplicationRepository;
import com.demo.repository.PlatformRepository;
import com.demo.repository.entity.AccountType;
import com.demo.repository.entity.Application;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ApplicationServiceTest {

    @Mock
    private AccountTypeRepository accountTypeRepository;

    @Mock
    private PlatformRepository platformRepository;

    @Mock
    private ApplicationRepository applicationRepository;

    @InjectMocks
    private ApplicationService applicationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    private ApplicationDto getValidDto() {
        return ApplicationDto.builder()
                .name("My App")
                .objectID("obj123")
                .applicationID("app456")
                .platform("Web")
                .accountType("Single Tenant")
                .redirectUrl("https://example.com")
                .build();
    }

    @Test
    void testRegisterApplication_success() {
        ApplicationDto dto = getValidDto();

        AccountType accountType = new AccountType();
        accountType.setId(1L);
        accountType.setName("Single Tenant");

        Platform platform = new Platform();
        platform.setId(1L);
        platform.setName("Web");

        Application saved = new Application();
        saved.setId(100L);
        saved.setName(dto.getName());
        saved.setApplicationId(dto.getApplicationID());
        saved.setObjectId(dto.getObjectID());
        saved.setRedirectUrl(dto.getRedirectUrl());
        saved.setAccountType(accountType);
        saved.setPlatform(platform);

        when(accountTypeRepository.findByName("Single Tenant")).thenReturn(Optional.of(accountType));
        when(platformRepository.findByName("Web")).thenReturn(Optional.of(platform));
        when(applicationRepository.save(any(Application.class))).thenReturn(saved);

        ApplicationDto result = applicationService.registerApplication(dto);

        assertNotNull(result);
        assertEquals("My App", result.getName());
        assertEquals("Single Tenant", result.getAccountType());
        assertEquals("Web", result.getPlatform());
        assertEquals("https://example.com", result.getRedirectUrl());
    }

    @Test
    void testRegisterApplication_accountTypeNotFound() {
        ApplicationDto dto = getValidDto();

        when(accountTypeRepository.findByName("Partner")).thenReturn(Optional.empty());

        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class, () ->
                        applicationService.registerApplication(dto));

        assertEquals("Account Type is not found", ex.getMessage());
    }

    @Test
    void testRegisterApplication_platformNotFound() {
        ApplicationDto dto = getValidDto();

        AccountType accountType = new AccountType();
        accountType.setName("Single Tenant");

        when(accountTypeRepository.findByName("Single Tenant")).thenReturn(Optional.of(accountType));
        when(platformRepository.findByName("Web")).thenReturn(Optional.empty());

        ResourceNotFoundException ex = assertThrows(ResourceNotFoundException.class, () ->
                        applicationService.registerApplication(dto));

        assertEquals("Platform is not found", ex.getMessage());
    }
}