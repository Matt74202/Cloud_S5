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
    

}
