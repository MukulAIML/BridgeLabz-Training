-- Address Book Database Schema
-- Compatible with SQLite, MySQL, and PostgreSQL

-- SQLite Schema
CREATE TABLE IF NOT EXISTS contacts (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    firstName TEXT NOT NULL,
    lastName TEXT NOT NULL,
    city TEXT,
    state TEXT,
    address TEXT,
    zip INTEGER,
    mobile BIGINT
);

-- MySQL Schema (uncomment if using MySQL)
-- CREATE TABLE IF NOT EXISTS contacts (
--     id INT AUTO_INCREMENT PRIMARY KEY,
--     firstName VARCHAR(100) NOT NULL,
--     lastName VARCHAR(100) NOT NULL,
--     city VARCHAR(100),
--     state VARCHAR(100),
--     address TEXT,
--     zip INT,
--     mobile BIGINT
-- );

-- PostgreSQL Schema (uncomment if using PostgreSQL)
-- CREATE TABLE IF NOT EXISTS contacts (
--     id SERIAL PRIMARY KEY,
--     firstName VARCHAR(100) NOT NULL,
--     lastName VARCHAR(100) NOT NULL,
--     city VARCHAR(100),
--     state VARCHAR(100),
--     address TEXT,
--     zip INTEGER,
--     mobile BIGINT
-- );

-- Indexes for faster sorting
CREATE INDEX IF NOT EXISTS idx_city ON contacts(city);
CREATE INDEX IF NOT EXISTS idx_state ON contacts(state);
CREATE INDEX IF NOT EXISTS idx_zip ON contacts(zip);
CREATE INDEX IF NOT EXISTS idx_fullname ON contacts(firstName, lastName);

-- Sample queries for verification
-- SELECT * FROM contacts ORDER BY firstName, lastName;
-- SELECT * FROM contacts ORDER BY city;
-- SELECT * FROM contacts ORDER BY state;
-- SELECT * FROM contacts ORDER BY zip;

