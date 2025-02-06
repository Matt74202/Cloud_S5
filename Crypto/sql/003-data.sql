INSERT INTO Utilisateur (nom, email, mdp) VALUES ('User 1', 'usercloudexemple@gmail.com', 'user1');

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

INSERT INTO Crypto (nom, valeur, date)
VALUES 
    ('Bitcoin', 45000.00, CURRENT_TIMESTAMP),
    ('Ethereum', 3000.00, CURRENT_TIMESTAMP),
    ('Solana', 150.00, CURRENT_TIMESTAMP),
    ('Cardano', 1.50, CURRENT_TIMESTAMP),
    ('Polkadot', 25.00, CURRENT_TIMESTAMP),
    ('Ripple', 1.00, CURRENT_TIMESTAMP),
    ('Dogecoin', 0.35, CURRENT_TIMESTAMP),
    ('Litecoin', 200.00, CURRENT_TIMESTAMP),
    ('Chainlink', 30.00, CURRENT_TIMESTAMP),
    ('Avalanche', 80.00, CURRENT_TIMESTAMP);

INSERT INTO TypeTransaction (type) VALUES 
('Entree'),         -- depot ou achat
('Sortie');         -- retrait ou vente

INSERT INTO TypeAnalyse (type) VALUES 
('1er quartile'),
('min'),
('max'),
('moyenne'),
('ecart-type');


