INSERT INTO Fond (id_utilisateur, solde) VALUES (1, 1000);

INSERT INTO Portefeuille (id_utilisateur, id_crypto, solde) VALUES (1, 1, 2);
INSERT INTO Portefeuille (id_utilisateur, id_crypto, solde) VALUES (1, 2, 2);
INSERT INTO Portefeuille (id_utilisateur, id_crypto, solde) VALUES (1, 3, 2);

INSERT INTO MouvementCrypto (id_utilisateur, id_type, date, id_crypto, quantite, etat) VALUES 
(1, 2, CURRENT_TIMESTAMP, 1, 4, 'en attente');