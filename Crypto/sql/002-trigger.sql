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


