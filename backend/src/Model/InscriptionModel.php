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

    public function insertInscription($nom,$email,$mdp) :int{
        $query = "insert into Inscription (nom,email,mdp) values(:nom ,:email,:mdp) ";
        $stmt = $this->connection->executeQuery($query, ['nom' =>$nom, 'email'=>$email , 'mdp'=> $mdp]);
        return $this->connection->lastInsertId();
    }

    public function getById($idInscription) {
        $query = "SELECT * FROM Inscription WHERE id = :idInscription ";
        $stmt = $this->connection->executeQuery($query, ['idInscription' => $idInscription]);
        return $stmt->fetchAssociative(); 
    }


}
