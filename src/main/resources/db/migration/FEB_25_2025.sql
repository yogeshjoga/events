CREATE TABLE gold_price
(
    id               BINARY(16)   NOT NULL,
    created          datetime     NULL,
    updated          datetime     NULL,
    timestamp        BIGINT       NOT NULL,
    metal            VARCHAR(255) NULL,
    currency         VARCHAR(255) NULL,
    exchange         VARCHAR(255) NULL,
    symbol           VARCHAR(255) NULL,
    prev_close_price DOUBLE       NOT NULL,
    open_price       DOUBLE       NOT NULL,
    low_price        DOUBLE       NOT NULL,
    high_price       DOUBLE       NOT NULL,
    open_time        BIGINT       NOT NULL,
    price            DOUBLE       NOT NULL,
    ch               DOUBLE       NOT NULL,
    chp              DOUBLE       NOT NULL,
    ask              DOUBLE       NOT NULL,
    bid              DOUBLE       NOT NULL,
    price_gram_24k   DOUBLE       NOT NULL,
    price_gram_22k   DOUBLE       NOT NULL,
    price_gram_21k   DOUBLE       NOT NULL,
    price_gram_20k   DOUBLE       NOT NULL,
    price_gram_18k   DOUBLE       NOT NULL,
    price_gram_16k   DOUBLE       NOT NULL,
    price_gram_14k   DOUBLE       NOT NULL,
    price_gram_10k   DOUBLE       NOT NULL,
    CONSTRAINT pk_gold_price PRIMARY KEY (id)
);

CREATE TABLE otp
(
    id          BINARY(16)   NOT NULL,
    created     datetime     NULL,
    updated     datetime     NULL,
    otp         VARCHAR(255) NULL,
    user_id     BINARY(16)   NULL,
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
    user_id      BINARY(16)   NULL,
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
    user_id      BINARY(16)   NULL,
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
    address    VARCHAR(255) NULL,
    phone      VARCHAR(255) NULL,
    user_id    BINARY(16)   NULL,
    state      VARCHAR(255) NULL,
    CONSTRAINT pk_relative PRIMARY KEY (id)
);

CREATE TABLE silver_price
(
    id               BINARY(16)   NOT NULL,
    created          datetime     NULL,
    updated          datetime     NULL,
    timestamp        BIGINT       NOT NULL,
    metal            VARCHAR(255) NULL,
    currency         VARCHAR(255) NULL,
    exchange         VARCHAR(255) NULL,
    symbol           VARCHAR(255) NULL,
    prev_close_price DOUBLE       NOT NULL,
    open_price       DOUBLE       NOT NULL,
    low_price        DOUBLE       NOT NULL,
    high_price       DOUBLE       NOT NULL,
    open_time        BIGINT       NOT NULL,
    price            DOUBLE       NOT NULL,
    ch               DOUBLE       NOT NULL,
    chp              DOUBLE       NOT NULL,
    ask              DOUBLE       NOT NULL,
    bid              DOUBLE       NOT NULL,
    price_gram_24k   DOUBLE       NOT NULL,
    price_gram_22k   DOUBLE       NOT NULL,
    price_gram_21k   DOUBLE       NOT NULL,
    price_gram_20k   DOUBLE       NOT NULL,
    price_gram_18k   DOUBLE       NOT NULL,
    price_gram_16k   DOUBLE       NOT NULL,
    price_gram_14k   DOUBLE       NOT NULL,
    price_gram_10k   DOUBLE       NOT NULL,
    CONSTRAINT pk_silver_price PRIMARY KEY (id)
);

CREATE TABLE user
(
    id         BINARY(16)   NOT NULL,
    created    datetime     NULL,
    updated    datetime     NULL,
    first_name VARCHAR(255) NULL,
    last_name  VARCHAR(255) NULL,
    user_name  VARCHAR(255) NOT NULL,
    password   VARCHAR(255) NOT NULL,
    email      VARCHAR(255) NOT NULL,
    state      VARCHAR(255) NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

ALTER TABLE otp
    ADD CONSTRAINT uc_otp_relative UNIQUE (relative_id);

ALTER TABLE otp
    ADD CONSTRAINT uc_otp_user UNIQUE (user_id);

ALTER TABLE user
    ADD CONSTRAINT uc_user_email UNIQUE (email);

ALTER TABLE user
    ADD CONSTRAINT uc_user_password UNIQUE (password);

ALTER TABLE user
    ADD CONSTRAINT uc_user_username UNIQUE (user_name);

ALTER TABLE otp
    ADD CONSTRAINT FK_OTP_ON_RELATIVE FOREIGN KEY (relative_id) REFERENCES relative (id);

ALTER TABLE otp
    ADD CONSTRAINT FK_OTP_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE presentations
    ADD CONSTRAINT FK_PRESENTATIONS_ON_RELATIVE FOREIGN KEY (relative_id) REFERENCES relative (id);

ALTER TABLE presentations
    ADD CONSTRAINT FK_PRESENTATIONS_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE receiving
    ADD CONSTRAINT FK_RECEIVING_ON_RELATIVE FOREIGN KEY (relative_id) REFERENCES relative (id);

ALTER TABLE receiving
    ADD CONSTRAINT FK_RECEIVING_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);

ALTER TABLE relative
    ADD CONSTRAINT FK_RELATIVE_ON_USER FOREIGN KEY (user_id) REFERENCES user (id);