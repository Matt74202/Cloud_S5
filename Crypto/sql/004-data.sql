INSERT INTO Utilisateur (nom, email, mdp) VALUES ('User 1', 'usercloudexemple@gmail.com', '123');

INSERT INTO Utilisateur (nom, email, mdp) VALUES 
('User 2', 'usercloudexemple@gmail.com', 'user2'),
('User 3', 'usercloudexemple@gmail.com', 'user3'),
('User 4', 'usercloudexemple@gmail.com', 'user4'),
('User 5', 'usercloudexemple@gmail.com', 'user5'),
('User 6', 'usercloudexemple@gmail.com', 'user6'),
('User 7', 'usercloudexemple@gmail.com', 'user7'),
('User 8', 'usercloudexemple@gmail.com', 'user8'),
('User 9', 'usercloudexemple@gmail.com', 'user9'),
('User 10', 'usercloudexemple@gmail.com', 'user10');

INSERT INTO Utilisateur (nom, email, mdp)
VALUES ('Mimi', 'test@gmail.com', digest('123', 'sha256'));

INSERT INTO Crypto (nom)
VALUES 
    ('Bitcoin'),
    ('Ethereum'),
    ('Solana'),
    ('Cardano'),
    ('Polkadot'),
    ('Ripple'),
    ('Dogecoin'),
    ('Litecoin'),
    ('Chainlink'),
    ('Avalanche');

INSERT INTO ValeurCrypto (id_crypto, valeur, date)
VALUES 
    (1, 45000.00, CURRENT_TIMESTAMP),
    (2, 3000.00, CURRENT_TIMESTAMP),
    (3, 150.00, CURRENT_TIMESTAMP),
    (4, 1.50, CURRENT_TIMESTAMP),
    (5, 25.00, CURRENT_TIMESTAMP),
    (6, 1.00, CURRENT_TIMESTAMP),
    (7, 0.35, CURRENT_TIMESTAMP),
    (8, 200.00, CURRENT_TIMESTAMP),
    (9, 30.00, CURRENT_TIMESTAMP),
    (10, 80.00, CURRENT_TIMESTAMP);

INSERT INTO TypeTransaction (type) VALUES 
('Entree'),         -- depot ou achat
('Sortie');         -- retrait ou vente

INSERT INTO TypeAnalyse (type) VALUES 
('1er quartile'),
('min'),
('max'),
('moyenne'),
('ecart-type');


