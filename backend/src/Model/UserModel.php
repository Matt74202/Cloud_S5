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
        $query="insert into Utilisateur (nom,email,mdp) values(:nom ,:email,:mdp)";
        $stmt = $this->connection->executeQuery($query, ['nom' => $inscription['nom'], 'email'=>$inscription['email'] , 'mdp'=> $inscription['mdp'] ]);
    }

    public function getById($idUser): array{
        $query = "SELECT * FROM Utilisateur WHERE id = :idUser ";
        $stmt = $this->connection->executeQuery($query, ['idUser' => $idUser]);
        return $stmt->fetchAssociative(); 
    }
    
    public function login($email, $mdp): ?array
    {
        $query = "SELECT * FROM Utilisateur WHERE email = :email";
        $stmt = $this->connection->executeQuery($query, ['email' => $email]);
        $user = $stmt->fetchAssociative();

        if (!$user) {
            return null; 
        }

        if ($user['mdp'] === $mdp) {
            return $user;
        } 

        return [
            'id' => $user['id'],
            'nom' => $user['nom'],
            'email' => null,
            'mdp' => null
        ];
    }


    public function updateUser($id, $nom, $mdp)
    {
        
            $hashedMdp = password_hash($mdp, PASSWORD_BCRYPT);
            $query = "UPDATE users SET nom = :nom, mdp = :mdp' WHERE id = :id";
            $result = $this->connection->executeQuery($query,['nom' => $nom, 'mdp' => $mdp]);
            return $result;
        
    }
}
