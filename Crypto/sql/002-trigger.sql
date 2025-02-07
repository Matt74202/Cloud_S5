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
