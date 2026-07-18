--liquibase formatted SQL

--changeset SergeiPerminov:create-schema runOnChange:false failOnError:true
--comment Create schema for inventory service
CREATE SCHEMA IF NOT EXISTS inventory_service;
--rollback DROP SCHEMA IF EXISTS inventory_service CASCADE;


--changeset SergeiPerminov:create-transactions-table
--comment create transactions table
CREATE TABLE inventory_service.transactions
(
    id          BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    user_login  TEXT                        NOT NULL,
    status      TEXT                        NOT NULL,
    created     TIMESTAMP                   NOT NULL
);
--rollback drop table transactions;


--changeset SergeiPerminov:create-transaction_abortreason-table
--comment create transaction_abortreason table
CREATE TABLE inventory_service.transaction_abortreason
(
    id                      BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    abort_reason            JSONB
);
--rollback drop table transaction_abortreason;

--changeset SergeiPerminov:create-transfer_by_locations-table
--comment create transfer_by_locations table
CREATE TABLE inventory_service.transfer_by_locations
(
    id                      BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    warehouse               TEXT                        NOT NULL,
    location_source         TEXT                        NOT NULL,
    location_target         TEXT                        NOT NULL,
    item                    TEXT                        NOT NULL,
    lot                     TEXT                        NOT NULL,
    quantity                DOUBLE PRECISION            NOT NULL,
    transaction_id          BIGINT REFERENCES inventory_service.transactions(id),
    abortreason_id          BIGINT REFERENCES inventory_service.transaction_abortreason(id)
);
--rollback drop table transfer_by_locations;

--changeset SergeiPerminov:create-placement_container-table
--comment create placement_container table
CREATE TABLE inventory_service.placement_container
(
    id                      BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    container_code          TEXT                        NOT NULL,
    bin_code_target         TEXT                        NOT NULL,
    transaction_id          BIGINT REFERENCES inventory_service.transactions(id),
    abortreason_id          BIGINT REFERENCES inventory_service.transaction_abortreason(id)
);
--rollback drop table placement_container;


