CREATE SCHEMA IF NOT EXISTS public;

CREATE TABLE IF NOT EXISTS location
(
    id      UUID PRIMARY KEY,
    country varchar(255),
    county  varchar(255),
    city    varchar(255)
);

CREATE TABLE IF NOT EXISTS user_table
(
    id         UUID PRIMARY KEY,
    password   VARCHAR(500),
    email      VARCHAR(50),
    first_name VARCHAR(50),
    last_name  VARCHAR(50)

);

CREATE TABLE IF NOT EXISTS profile_picture
(
    id              UUID PRIMARY KEY,
    profile_picture BYTEA,
    user_id         UUID,
    FOREIGN KEY (user_id) REFERENCES user_table (id)
);


CREATE TABLE IF NOT EXISTS chat
(
    id       UUID PRIMARY KEY,
    user1_id UUID,
    user2_id UUID,
    FOREIGN KEY (user1_id) REFERENCES user_table (id),
    FOREIGN KEY (user2_id) REFERENCES user_table (id)
);


CREATE TABLE IF NOT EXISTS message
(
    id        UUID PRIMARY KEY,
    sender_id UUID,
    chat_id   UUID,
    content   varchar(1000),
    sent_at   timestamp,
    FOREIGN KEY (sender_id) REFERENCES user_table (id),
    FOREIGN KEY (chat_id) REFERENCES chat (id)
);

CREATE TABLE IF NOT EXISTS car
(
    id               UUID PRIMARY KEY,
    location_id      UUID,
    price            INT,
    predicted_price  INT,
    manufacturer     VARCHAR(255),
    model            VARCHAR(255),
    prod_year        INT,
    category         VARCHAR(255),
    leather_interior BOOLEAN,
    fuel_type        VARCHAR(255),
    engine_volume    DECIMAL(10, 2),
    mileage          INT,
    cylinders        INT,
    gear_box_type    VARCHAR(255),
    drive_wheels     VARCHAR(255),
    doors            VARCHAR(255),
    wheel            VARCHAR(255),
    color            VARCHAR(255),
    airbags          INT,
    is_turbo         BOOLEAN,

    FOREIGN KEY (location_id) REFERENCES location (id)
);


CREATE TABLE IF NOT EXISTS selling_post
(
    id          UUID PRIMARY KEY,
    owner_id    UUID,
    car_id      UUID,
    available   varchar(3),
    title       varchar(255),
    description varchar(1000),
    post_date   timestamp,
    FOREIGN KEY (owner_id) REFERENCES user_table (id),
    FOREIGN KEY (car_id) REFERENCES car (id)
);

CREATE TABLE IF NOT EXISTS selling_picture
(
    id              UUID PRIMARY KEY,
    picture         BYTEA,
    selling_post_id UUID,
    FOREIGN KEY (selling_post_id) REFERENCES selling_post (id)
);