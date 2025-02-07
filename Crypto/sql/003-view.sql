------------------------------------------------------chaque crypto avec sa derniere valeur---------------------------------------------------
CREATE OR REPLACE VIEW v_crypto_last AS
SELECT 
    c.id, 
    c.nom, 
    v.valeur, 
    v.date
FROM 
    Crypto c
JOIN 
    ValeurCrypto v ON c.id = v.id_crypto  -- Correction ici : id_crypto au lieu de id
WHERE 
    v.date = (
        SELECT MAX(v2.date)
        FROM ValeurCrypto v2
        WHERE v2.id_crypto = c.id  -- Lien avec la crypto courante
    );


-----------------------------------------Valeur totale de cryptomonnaie qu'un user possede----------------------------------------------------
CREATE OR REPLACE VIEW v_portefeuille_user AS
SELECT 
    u.id AS utilisateur_id,
    u.nom AS utilisateur_nom,
    SUM(p.solde * v.valeur) AS valeur_totale
FROM 
    Utilisateur u
JOIN 
    Portefeuille p ON u.id = p.id_utilisateur
JOIN 
    v_crypto_last v ON p.id_crypto = v.id
GROUP BY 
    u.id, u.nom;



