----------------------------------------CREATION DE FOND POUR CHAQUE USER INSERE-------------------------------------------------------------
CREATE OR REPLACE FUNCTION create_fond_after_user()
RETURNS TRIGGER AS $$
BEGIN
    INSERT INTO Fond (id_utilisateur, solde)
    VALUES (NEW.id, 0);
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_create_fond
AFTER INSERT ON Utilisateur
FOR EACH ROW
EXECUTE FUNCTION create_fond_after_user();

------------------------------------------------UPDATE DE SOLDE A CHAQUE MOUVEMENT FOND-------------------------------------------------------
CREATE OR REPLACE FUNCTION update_fond_solde() 
RETURNS TRIGGER AS $$
BEGIN

    IF NEW.id_type = 1 THEN

        UPDATE Fond 
        SET solde = solde + NEW.montant 
        WHERE id_utilisateur = NEW.id_utilisateur;

    ELSIF NEW.id_type = 2 THEN

        UPDATE Fond 
        SET solde = solde - NEW.montant 
        WHERE id_utilisateur = NEW.id_utilisateur;
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_update_fond_solde
AFTER INSERT ON MouvementFond
FOR EACH ROW
EXECUTE FUNCTION update_fond_solde();

-----------------------------------------------------Calcul du montant pour chaque mouvement crypto--------------------------------------------
CREATE OR REPLACE FUNCTION calculer_montant_crypto()
RETURNS TRIGGER AS $$
DECLARE
    valeur_actuelle DECIMAL;
BEGIN
    -- Récupérer la valeur actuelle de la crypto à partir de la vue v_crypto_last
    SELECT v.valeur
    INTO valeur_actuelle
    FROM v_crypto_last v
    WHERE v.id = NEW.id_crypto;

    -- Calculer et mettre à jour le montant dans la nouvelle ligne
    NEW.montant := NEW.quantite * valeur_actuelle;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trg_calculer_montant_crypto
BEFORE INSERT ON MouvementCrypto
FOR EACH ROW
EXECUTE FUNCTION calculer_montant_crypto();


----------------------------------------------------UPDATE DE SOLDE FOND ET CRYPTO A CHAQUE MOUVEMENT CRYPTO------------------------------------
ALTER TABLE Portefeuille 
ADD CONSTRAINT portefeuille_unique UNIQUE (id_utilisateur, id_crypto);


CREATE OR REPLACE FUNCTION process_mouvement_crypto()
RETURNS TRIGGER AS $$
DECLARE
    crypto_value DECIMAL;
BEGIN
    -- Vérifier si c'est un achat
    IF NEW.id_type = 1 THEN -- Achat
        
        -- Vérification du solde dans la table Fond
        PERFORM 1 FROM Fond 
        WHERE id_utilisateur = NEW.id_utilisateur 
        AND solde >= NEW.montant;  -- Utilisation de NEW.montant directement
        
        IF NOT FOUND THEN
            RAISE EXCEPTION 'Solde insuffisant pour l''achat';
        END IF;

        -- Mise à jour du solde dans la table Fond
        UPDATE Fond SET solde = solde - NEW.montant 
        WHERE id_utilisateur = NEW.id_utilisateur;

        -- Mise à jour ou insertion dans le Portefeuille
        INSERT INTO Portefeuille (id_utilisateur, id_crypto, solde)  
        VALUES (NEW.id_utilisateur, NEW.id_crypto, NEW.quantite)  
        ON CONFLICT (id_utilisateur, id_crypto)  
        DO UPDATE SET solde = Portefeuille.solde + EXCLUDED.solde;

    -- Vérifier si c'est une vente
    ELSIF NEW.id_type = 2 THEN -- Vente
        
        -- Vérification de la quantité de crypto dans le Portefeuille
        PERFORM 1 FROM Portefeuille 
        WHERE id_utilisateur = NEW.id_utilisateur 
        AND id_crypto = NEW.id_crypto  
        AND solde >= NEW.quantite;
        
        IF NOT FOUND THEN
            RAISE EXCEPTION 'Quantité crypto insuffisante pour la vente';
        END IF;

        -- Mise à jour du solde dans la table Fond après vente
        UPDATE Fond SET solde = solde + NEW.montant  -- Utilisation de NEW.montant directement
        WHERE id_utilisateur = NEW.id_utilisateur;

        -- Mise à jour du solde dans le Portefeuille après vente
        UPDATE Portefeuille SET solde = solde - NEW.quantite 
        WHERE id_utilisateur = NEW.id_utilisateur 
        AND id_crypto = NEW.id_crypto;

        -- Suppression de l'entrée si la quantité de crypto est à zéro
        DELETE FROM Portefeuille 
        WHERE id_utilisateur = NEW.id_utilisateur 
        AND id_crypto = NEW.id_crypto  
        AND solde = 0;
        
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Création du trigger lié à l'insertion dans MouvementCrypto
CREATE TRIGGER mouvement_crypto_trigger
AFTER INSERT ON MouvementCrypto
FOR EACH ROW EXECUTE FUNCTION process_mouvement_crypto();



