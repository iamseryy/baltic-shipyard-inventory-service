--liquibase formatted sql


--changeset SergeiPerminov:create-schema
--comment create new schema
CREATE SCHEMA inventory_service;
--rollback drop schema baltic_shipyard_inventory_service;


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



--changeset SergeiPerminov:create-updatable_measuredremainder-table
--comment create updatable_measuredremainder table
CREATE TABLE inventory_service.updatable_measuredremainder
(
    id                      BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    measuredremainder_id    TEXT                        NOT NULL,
    remainder               TEXT                        NOT NULL,
    project                 TEXT                        NOT NULL,
    material                TEXT                        NOT NULL,
    warehouse               TEXT                        NOT NULL,
    location                TEXT                        NOT NULL,
    sequence                INT                         NOT NULL,
    status                  TEXT                        NOT NULL,
    comment                 TEXT                        NOT NULL,
    length                  DOUBLE PRECISION            NOT NULL,
    width                   DOUBLE PRECISION            NOT NULL,
    depth                   DOUBLE PRECISION            NOT NULL,
    transaction_id          BIGINT REFERENCES inventory_service.transactions(id),
    abortreason_id          BIGINT REFERENCES inventory_service.transaction_abortreason(id)
);
--rollback drop table updatable_measuredremainder;

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
--rollback drop table transfer_by_locations;


