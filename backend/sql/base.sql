CREATE DATABASE Cloud;
\c cloud

CREATE TABLE Role(
    id SERIAL PRIMARY KEY, 
    nom VARCHAR(30) NOT NULL
);

CREATE TABLE Utilisateur(
    id SERIAL PRIMARY KEY, 
    nom VARCHAR(30),
    email VARCHAR(50) NOT NULL,
    mdp VARCHAR(255) NOT NULL
);

CREATE TABLE Tentative(
    id SERIAL PRIMARY KEY,
    nombre INT,
    id_utilisateur INT REFERENCES Utilisateur(id)
);

CREATE TABLE Token(
    id SERIAL PRIMARY KEY,
    token VARCHAR(50) NOT NULL,
    date_creation TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP, 
    date_expiration TIMESTAMP WITHOUT TIME ZONE NOT NULL
);

CREATE TABLE Pin (
    id SERIAL PRIMARY KEY,
    id_utilisateur INT NOT NULL REFERENCES Utilisateur(id),
    pin VARCHAR(6) NOT NULL,
    date_expiration TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    date_creation TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Inscription(
    id SERIAL PRIMARY KEY,
    nom VARCHAR(30),
    email VARCHAR(50) NOT NULL,
    mdp VARCHAR(255) NOT NULL,
    valide boolean DEFAULT false 
);

