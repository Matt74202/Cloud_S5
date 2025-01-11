<?php

namespace App\Model;

use App\Helper\DatabaseConnection;
use App\Model\InscriptionModel;

class TentativeModel
{
    private $connection;
    private $userModel;

    public function __construct(UserModel $userModel)
    {
        $this->connection = DatabaseConnection::getConnection();
        $this->userModel= $userModel;
    }

    public function getTentativeByIdUser($idUser) {
        $query = "SELECT * FROM Tentative WHERE id_utilisateur = :id_utilisateur";
        $stmt= $this->connection->executeQuery($query, ['id_utilisateur' => $idUser]);
        return $stmt->fetchAssociative();
    }
    
    public function addTentative($idUser) {
        $tentative = $this->getTentativeByIdUser($idUser);
    
        if ($tentative) {
            $nombre = $tentative['nombre'] + 1;
            $queryUpdate = "UPDATE Tentative SET nombre = :nombre WHERE id_utilisateur = :id_utilisateur";
            $stmt = $this->connection->executeQuery($queryUpdate, ['nombre' => $nombre, 'id_utilisateur' => $idUser]);
        } else {
            $queryInsert = "INSERT INTO Tentative (nombre, id_utilisateur) VALUES (:nombre, :id_utilisateur)";
            $stmt = $this->connection->executeQuery($queryInsert, ['nombre' => 1, 'id_utilisateur' => $idUser]);
        }
    }
    

    public function resetTentative($idUser){
        $tentative = $this->getTentativeByIdUser($idUser);
        $queryUpdate = "UPDATE Tentative SET nombre = 0 WHERE id_utilisateur = :id_utilisateur";
        $stmt = $this->connection->executeQuery($queryUpdate, ['id_utilisateur' => $idUser]);
    }

    public function checkTentative($idUser, $contenuEmail) {
        $tentative = $this->getTentativeByIdUser($idUser);
    
        if ($tentative && $tentative['nombre'] >= 3) {
            $emailModel = new EmailModel();
            $emailModel->sendEmail($idUser, $contenuEmail);
            return false;
        } 
        else {
            $this->addTentative($idUser);
            return true;
        }
    }
}
