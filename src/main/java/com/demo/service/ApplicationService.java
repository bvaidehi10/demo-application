package com.demo.service;

import com.demo.repository.entity.Platform;
import com.demo.dto.ApplicationDto;
import com.demo.exceptions.ResourceNotFoundException;
import com.demo.repository.AccountTypeRepository;
import com.demo.repository.ApplicationRepository;
import com.demo.repository.PlatformRepository;
import com.demo.repository.entity.AccountType;
import com.demo.repository.entity.Application;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ApplicationService {

    private final AccountTypeRepository accountTypeRepository;

    private final PlatformRepository platformRepository;

    private final ApplicationRepository applicationRepository;

    public ApplicationService(AccountTypeRepository accountTypeRepository,
                              PlatformRepository platformRepository,
                              ApplicationRepository applicationRepository) {
        this.accountTypeRepository = accountTypeRepository;
        this.platformRepository = platformRepository;
        this.applicationRepository = applicationRepository;
    }

    public ApplicationDto registerApplication(ApplicationDto request) {

        AccountType accountType = accountTypeRepository.findByName(request.getAccountType())
                .orElseThrow(() -> new ResourceNotFoundException("Account Type is not found"));

        Platform platform = platformRepository.findByName(request.getPlatform())
                .orElseThrow(() -> new ResourceNotFoundException("Platform is not found"));

        Application application = new Application();
        application.setName(request.getName());
        application.setObjectId(request.getObjectID());
        application.setApplicationId(request.getApplicationID());
        application.setPlatform(platform);
        application.setAccountType(accountType);
        application.setRedirectUrl(request.getRedirectUrl());

        return toDto(applicationRepository.save(application));
    }

    private ApplicationDto toDto(Application application) {
        return ApplicationDto.builder()
                .id(application.getId())
                .name(application.getName())
                .redirectUrl(application.getRedirectUrl())
                .objectID(application.getObjectId())
                .accountType(application.getAccountType().getName())
                .applicationID(application.getApplicationId())
                .platform(application.getPlatform().getName())
                .build();
    }


}
