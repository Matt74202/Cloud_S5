CREATE OR REPLACE FUNCTION reset_inscription() 
RETURNS void AS $$
BEGIN
    -- Supprimer toutes les lignes dans la table Inscription
    DELETE FROM Inscription;

    -- Réinitialiser l'incrémentation de l'ID (la séquence)
    ALTER SEQUENCE inscription_id_seq RESTART WITH 1;
END;
$$ LANGUAGE plpgsql;
