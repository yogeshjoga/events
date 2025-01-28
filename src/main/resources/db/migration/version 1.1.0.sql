CREATE TABLE presentations
(
    id           BIGINT       NOT NULL,
    created      datetime     NULL,
    updated      datetime     NULL,
    gold_in_gm   DOUBLE       NOT NULL,
    silver_in_gm DOUBLE       NOT NULL,
    amount       DOUBLE       NOT NULL,
    objects      VARCHAR(255) NULL,
    relative_id  BIGINT       NULL,
    CONSTRAINT pk_presentations PRIMARY KEY (id)
);

CREATE TABLE receiving
(
    id           BIGINT       NOT NULL,
    created      datetime     NULL,
    updated      datetime     NULL,
    gold_in_gm   DOUBLE       NOT NULL,
    silver_in_gm DOUBLE       NOT NULL,
    amount       DOUBLE       NOT NULL,
    objects      VARCHAR(255) NULL,
    relative_id  BIGINT       NULL,
    CONSTRAINT pk_receiving PRIMARY KEY (id)
);

CREATE TABLE relative
(
    id      BIGINT       NOT NULL,
    created datetime     NULL,
    updated datetime     NULL,
    name    VARCHAR(255) NULL,
    address VARCHAR(255) NULL,
    phone   VARCHAR(255) NULL,
    CONSTRAINT pk_relative PRIMARY KEY (id)
);

ALTER TABLE presentations
    ADD CONSTRAINT FK_PRESENTATIONS_ON_RELATIVE FOREIGN KEY (relative_id) REFERENCES relative (id);

ALTER TABLE receiving
    ADD CONSTRAINT FK_RECEIVING_ON_RELATIVE FOREIGN KEY (relative_id) REFERENCES relative (id);