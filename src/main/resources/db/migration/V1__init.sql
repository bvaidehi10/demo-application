CREATE TABLE account_type (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    created_date DATETIME,
    updated_date DATETIME,
    deleted_flag BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE platform (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    created_date DATETIME,
    updated_date DATETIME,
    deleted_flag BOOLEAN NOT NULL DEFAULT FALSE
);

CREATE TABLE application (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    application_id VARCHAR(255) NOT NULL,
    object_id VARCHAR(255) NOT NULL,
    redirect_url VARCHAR(500),

    account_type_id BIGINT NOT NULL,
    platform_id BIGINT NOT NULL,

    created_date DATETIME,
    updated_date DATETIME,
    deleted_flag BOOLEAN NOT NULL DEFAULT FALSE,

    CONSTRAINT fk_application_account_type FOREIGN KEY (account_type_id) REFERENCES account_type(id),
    CONSTRAINT fk_application_platform FOREIGN KEY (platform_id) REFERENCES platform(id)
);