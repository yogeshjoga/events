CREATE TABLE otp
(
    id          BINARY(16)   NOT NULL,
    created     datetime     NULL,
    updated     datetime     NULL,
    otp         VARCHAR(255) NULL,
    relative_id BINARY(16)   NULL,
    CONSTRAINT pk_otp PRIMARY KEY (id)
);

CREATE TABLE presentations
(
    id           BINARY(16)   NOT NULL,
    created      datetime     NULL,
    updated      datetime     NULL,
    gold_in_gm   DOUBLE       NOT NULL,
    silver_in_gm DOUBLE       NOT NULL,
    amount       DOUBLE       NOT NULL,
    objects      VARCHAR(255) NULL,
    relative_id  BINARY(16)   NULL,
    CONSTRAINT pk_presentations PRIMARY KEY (id)
);

CREATE TABLE receiving
(
    id           BINARY(16)   NOT NULL,
    created      datetime     NULL,
    updated      datetime     NULL,
    gold_in_gm   DOUBLE       NOT NULL,
    silver_in_gm DOUBLE       NOT NULL,
    amount       DOUBLE       NOT NULL,
    objects      VARCHAR(255) NULL,
    relative_id  BINARY(16)   NULL,
    CONSTRAINT pk_receiving PRIMARY KEY (id)
);

CREATE TABLE relative
(
    id         BINARY(16)   NOT NULL,
    created    datetime     NULL,
    updated    datetime     NULL,
    first_name VARCHAR(255) NULL,
    last_name  VARCHAR(255) NULL,
    city       VARCHAR(255) NULL,
    user_name  VARCHAR(255) NOT NULL,
    address    VARCHAR(255) NULL,
    phone      VARCHAR(255) NULL,
    email      VARCHAR(255) NULL,
    password   VARCHAR(255) NULL,
    state      VARCHAR(255) NULL,
    CONSTRAINT pk_relative PRIMARY KEY (id)
);

ALTER TABLE otp
    ADD CONSTRAINT uc_otp_relative UNIQUE (relative_id);

ALTER TABLE relative
    ADD CONSTRAINT uc_relative_email UNIQUE (email);

ALTER TABLE relative
    ADD CONSTRAINT uc_relative_phone UNIQUE (phone);

ALTER TABLE relative
    ADD CONSTRAINT uc_relative_username UNIQUE (user_name);

ALTER TABLE otp
    ADD CONSTRAINT FK_OTP_ON_RELATIVE FOREIGN KEY (relative_id) REFERENCES relative (id);

ALTER TABLE presentations
    ADD CONSTRAINT FK_PRESENTATIONS_ON_RELATIVE FOREIGN KEY (relative_id) REFERENCES relative (id);

ALTER TABLE receiving
    ADD CONSTRAINT FK_RECEIVING_ON_RELATIVE FOREIGN KEY (relative_id) REFERENCES relative (id);