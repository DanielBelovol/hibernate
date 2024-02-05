CREATE TABLE clients (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(200) NOT NULL
);

CREATE TABLE planets (
    id VARCHAR(40) NOT NULL,
    name VARCHAR(500) NOT NULL,
    PRIMARY KEY (id),
    CHECK (id = UPPER(id))
);

CREATE TABLE tickets (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    client_id BIGINT NOT NULL,
    from_planet_id VARCHAR(40) NOT NULL,
    to_planet_id VARCHAR(40) NOT NULL,
    FOREIGN KEY (client_id) REFERENCES clients (id),
    FOREIGN KEY (from_planet_id) REFERENCES planets (id),
    FOREIGN KEY (to_planet_id) REFERENCES planets (id)
);
