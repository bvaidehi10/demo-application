package com.demo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApplicationDto {
    private Long id;
    @NotBlank
    private String name;

    @NotBlank
    private String applicationID;

    @NotBlank
    private String objectID;

    private String redirectUrl;

    @NotBlank
    private String platform;

    @NotBlank
    private String accountType;

//    public static class ApplicationDtoBuilder {
//        private Long id;
//        private String name;
//        private String applicationID;
//        private String objectID;
//        private String redirectUrl;
//        private String platform;
//        private String accountType;
//
//        public ApplicationDtoBuilder id(Long id) {
//            this.id = id;
//            return this;
//        }
//
//        public ApplicationDtoBuilder name(String name) {
//            this.name = name;
//            return this;
//        }
//
//        public ApplicationDtoBuilder applicationID(String applicationID) {
//            this.applicationID = applicationID;
//            return this;
//        }
//
//        public ApplicationDtoBuilder objectID(String objectID) {
//            this.objectID = objectID;
//            return this;
//        }
//
//        public ApplicationDtoBuilder redirectUrl(String redirectUrl) {
//            this.redirectUrl = redirectUrl;
//            return this;
//        }
//
//        public ApplicationDtoBuilder platform(String platform) {
//            this.platform = platform;
//            return this;
//        }
//
//        public ApplicationDtoBuilder accountType(String accountType) {
//            this.accountType = accountType;
//            return this;
//        }
//
//        public ApplicationDto build() {
//            ApplicationDto dto = new ApplicationDto();
//            dto.setId(id);
//            dto.setName(name);
//            dto.setApplicationID(applicationID);
//            dto.setObjectID(objectID);
//            dto.setRedirectUrl(redirectUrl);
//            dto.setPlatform(platform);
//            dto.setAccountType(accountType);
//            return dto;
//        }
//    }

}
