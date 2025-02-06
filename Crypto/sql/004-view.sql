-----------------------------------------Valeur totale de cryptomonnaie qu'un user possede----------------------------------------------------
CREATE VIEW v_portefeuille_user AS
SELECT 
    u.id AS utilisateur_id,
    u.nom AS utilisateur_nom,
    SUM(p.solde * c.valeur) AS valeur_totale
FROM 
    Utilisateur u
JOIN 
    Portefeuille p ON u.id = p.id_utilisateur
JOIN 
    Crypto c ON p.idCrypto = c.id
GROUP BY 
    u.id, u.nom;


