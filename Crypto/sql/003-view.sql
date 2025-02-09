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

---------------------------------------------------Historique des valeurs des cryptos---------------------------------------------------------
CREATE OR REPLACE VIEW v_crypto_historique AS
SELECT 
    c.id, 
    c.nom, 
    v.valeur, 
    v.date
FROM 
    Crypto c
JOIN 
    ValeurCrypto v ON c.id = v.id_crypto ;


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

--------------------------------------------------Total des ventes par user------------------------------------------------------------------
CREATE OR REPLACE VIEW v_total_vente AS
SELECT 
    u.id AS utilisateur_id,
    u.nom AS utilisateur_nom,
    SUM(m.quantite * v.valeur) AS total_vente
FROM 
    Utilisateur u
JOIN 
    MouvementCrypto m ON u.id = m.id_utilisateur
JOIN 
    v_crypto_last v ON m.id_crypto = v.id
JOIN 
    TypeTransaction t ON m.id_type = t.id
WHERE 
    m.id_type = 2  
GROUP BY 
    u.id, u.nom;

----------------------------------------------------------Total des achats par user------------------------------------------------------------
CREATE OR REPLACE VIEW v_total_achat AS
SELECT 
    u.id AS utilisateur_id,
    u.nom AS utilisateur_nom,
    SUM(m.quantite * v.valeur) AS total_achat
FROM 
    Utilisateur u
JOIN 
    MouvementCrypto m ON u.id = m.id_utilisateur
JOIN 
    v_crypto_last v ON m.id_crypto = v.id
JOIN 
    TypeTransaction t ON m.id_type = t.id
WHERE 
    m.id_type = 1
GROUP BY 
    u.id, u.nom;

------------------------------------------------------------DETAILS MIARAKA-------------------------------------------------------------------
CREATE OR REPLACE VIEW v_user_transaction_summary AS
SELECT 
    u.id AS utilisateur_id,
    u.nom AS utilisateur_nom,
    COALESCE(vv.total_vente, 0) AS total_vente,  -- Total des ventes
    COALESCE(va.total_achat, 0) AS total_achat,  -- Total des achats
    COALESCE(pu.valeur_totale, 0) AS valeur_portefeuille  -- Valeur totale du portefeuille
FROM 
    Utilisateur u
LEFT JOIN 
    v_total_vente vv ON u.id = vv.utilisateur_id
LEFT JOIN 
    v_total_achat va ON u.id = va.utilisateur_id
LEFT JOIN 
    v_portefeuille_user pu ON u.id = pu.utilisateur_id;




