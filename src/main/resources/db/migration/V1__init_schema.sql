CREATE TABLE match (
    id SERIAL PRIMARY KEY,
    description VARCHAR(255),
    match_date DATE,
    match_time TIME,
    team_a VARCHAR(100),
    team_b VARCHAR(100),
    sport INT
);

CREATE TABLE match_odds (
    id SERIAL PRIMARY KEY,
    match_id INT NOT NULL REFERENCES match(id) ON DELETE CASCADE,
    specifier VARCHAR(10),
    odd NUMERIC(5,2)
);