<?php

namespace App\Model;

use App\Helper\DatabaseConnection;

class InscriptionModel
{
    private $connection;

    public function __construct()
    {
        $this->connection = DatabaseConnection::getConnection();
    }

    public function InsertInscription($nom,$email,$mdp): array{
        $valide = false;
        $query = "insert into Inscription(nom,email,mdp,valide) values(:nom ,:email,:mdp,:valide ) ";
        $stmt = $this->connection->executeQuery($query, ['nom' =>$nom, 'email'=>$email , 'mdp'=> $mdp , 'valide'=>$valide]);
        return $stmt->fetchAssociative();
    }

    public function getById($idInscription): array{
        $query = "SELECT * FROM Inscription WHERE idInscription = :idInscription ";
        $stmt = $this->connection->executeQuery($query, ['idInscription' => $idInscription]);
        return $stmt->fetchAssociative(); 
    }
    

}
