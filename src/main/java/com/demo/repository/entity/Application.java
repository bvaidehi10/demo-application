package com.demo.repository.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Objects;

@Entity
public class Application  extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    //@GeneratedValue(strategy = GenerationType.UUID)
    @Column(unique = true)
    private String applicationId;

    @NotBlank
    @Column(unique = true)
    //@GeneratedValue(strategy = GenerationType.UUID)
    private String objectId;

    private String redirectUrl;

    @ManyToOne
    @JoinColumn(name = "platform_id", nullable = false)
    private Platform platform;

    @ManyToOne
    @JoinColumn(name = "account_type_id", nullable = false)
    private AccountType accountType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(String applicationId) {
        this.applicationId = applicationId;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getRedirectUrl() {
        return redirectUrl;
    }

    public void setRedirectUrl(String redirectUrl) {
        this.redirectUrl = redirectUrl;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    public AccountType getAccountType() {
        return accountType;
    }

    public void setAccountType(AccountType accountType) {
        this.accountType = accountType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Application that = (Application) o;
        return Objects.equals(name, that.name)
                && Objects.equals(applicationId, that.applicationId)
                && Objects.equals(objectId, that.objectId)
                && Objects.equals(redirectUrl, that.redirectUrl)
                && Objects.equals(platform, that.platform)
                && Objects.equals(accountType, that.accountType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, applicationId, objectId, redirectUrl, platform, accountType);
    }

    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", applicationID='" + applicationId + '\'' +
                ", objectID='" + objectId + '\'' +
                ", redirectUrl='" + redirectUrl + '\'' +
                ", platform=" + platform +
                ", accountType=" + accountType +
                '}';
    }
}
