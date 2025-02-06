CREATE TABLE Utilisateur(
    id SERIAL PRIMARY KEY, 
    nom VARCHAR(30),
    email VARCHAR(50) NOT NULL,
    mdp VARCHAR(255) NOT NULL
);

CREATE TABLE Crypto(
    id SERIAL PRIMARY KEY,
    nom VARCHAR(30) NOT NULL,
    valeur DECIMAL,
    date TIMESTAMP
);

CREATE TABLE Fond(
    id SERIAL PRIMARY KEY,
    id_utilisateur INT REFERENCES Utilisateur(id),
    solde DECIMAL
);

CREATE TABLE Portefeuille(
    id SERIAL PRIMARY KEY,
    id_utilisateur INT REFERENCES Utilisateur(id),
    solde DECIMAL,
    idCrypto int references Crypto(id)
);

CREATE TABLE TypeTransaction(
    id SERIAL PRIMARY KEY,
    type VARCHAR(30)
);

CREATE TABLE MouvementCrypto(
    id SERIAL PRIMARY KEY,
    id_utilisateur INT REFERENCES Utilisateur(id),
    id_type INT REFERENCES TypeTransaction(id),
    date TIMESTAMP,
    idCrypto int references Crypto(id),
    montant DECIMAL
);

CREATE TABLE MouvementFond(
    id SERIAL PRIMARY KEY,
    id_utilisateur INT REFERENCES Utilisateur(id),
    id_type INT REFERENCES TypeTransaction(id),
    date TIMESTAMP,
    idFond int references Fond(id),
    montant DECIMAL
);

CREATE TABLE TypeAnalyse(
    id SERIAL PRIMARY KEY,
    type VARCHAR(30)
);

CREATE TABLE Analyse(
    id SERIAL PRIMARY KEY,
    id_type INT REFERENCES TypeAnalyse(id),
    id_crypto INT REFERENCES Crypto(id),
    date_min TIMESTAMP,
    date_max TIMESTAMP
);

CREATE TABLE Commission(
    id SERIAL PRIMARY KEY,
    pourcentage_vente DECIMAL,
    pourcentage_achat DECIMAL
);

CREATE TABLE Admin(
    id SERIAL PRIMARY KEY,
    email VARCHAR(50) NOT NULL,
    mdp VARCHAR(50) NOT NULL
);

CREATE TABLE Notification(
    id SERIAL PRIMARY KEY,
    contenu VARCHAR(200) NOT NULL,
    id_utilisateur INT REFERENCES Utilisateur(id),
    date TIMESTAMP
);

CREATE TABLE Favori(
    id SERIAL PRIMARY KEY,
    id_utilisateur INT REFERENCES Utilisateur(id),
    id_crypto INT REFERENCES Crypto(id)
);