<?php

namespace App\Model;

use App\Helper\DatabaseConnection;
use App\Model\InscriptionModel;

class UserModel
{
    private $connection;
    private $inscriptionModel;

    public function __construct(InscriptionModel $inscriptionModel)
    {
        $this->connection = DatabaseConnection::getConnection();
        $this->inscriptionModel= $inscriptionModel;
    }

    public function insertUser($idInscription){
        $inscription=$this->inscriptionModel->getById($idInscription);
        $query="insert into Users(nom,email,mdp) values(:nom ,:email,:mdp)";
        $stmt = $this->connection->executeQuery($query, ['nom' => $inscription['nom'], 'email'=>$inscription['email'] , 'mdp'=> $inscription['mdp'] ]);
    }

    public function getById($idInscription): array{
        $query = "SELECT * FROM Inscription WHERE idInscription = :idInscription ";
        $stmt = $this->connection->executeQuery($query, ['idInscription' => $idInscription]);
        return $stmt->fetchAssociative(); 
    }
    
    function login($mysqli, $email, $mdp) {
        $email = $mysqli->real_escape_string($email);
        $sql = "SELECT * FROM Users WHERE email = '$email'";
        $result = $mysqli->query($sql);
        if ($result->num_rows > 0) {
            $user = $result->fetch_assoc();
            if (password_verify($mdp, $user['password'])) {
                echo "Connexion réussie pour l'utilisateur : " . $user['email'] . "\n";
                return $user;
            } else {
                echo "Mot de passe incorrect.\n";
                return null;
            }
        } else {
            echo "Aucun utilisateur trouvé avec cet email.\n";
            return null;
        }
    }
    public function updateUser($id, $nom, $mdp)
    {
        
            $hashedMdp = password_hash($mdp, PASSWORD_BCRYPT);
            $query = "UPDATE users SET nom = :nom, mdp = :mdp' WHERE id = :id";
            $result = $this->connection->executeQuery($query,['nom' => $nom, 'mdp' => $mdp]);
            return $result;
        
    }
}
