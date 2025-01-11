<?php
namespace App\Model;

use App\Helper\DatabaseConnection;
use \DateTime;

class TokenModel
{
    private $connection;

    public function __construct()
    {
        $this->connection = DatabaseConnection::getConnection();
    }

    public function insert($token, $date_creation, $date_expiration): int
    {
        $query = "INSERT INTO Token (token, date_creation, date_expiration) VALUES (:token, :date_creation, :date_expiration)";
        $stmt = $this->connection->executeQuery($query, ['token' => $token, 'date_creation'=>$date_creation , 'date_expiration'=> $date_expiration]);
        return (int)$this->connection->lastInsertId(); 
    }

    public function getById(int $id): ?array
    {

        $query = "SELECT * FROM Token WHERE id = :id";
        $stmt= $this->connection->executeQuery($query,['id' =>$id]); 
        return $stmt->fetchAssociative(); 
    }
    
    public function generateToken(int $idUser)
    {
        $token = bin2hex(random_bytes(32));
        $date_creation = (new DateTime())->format('Y-m-d H:i:s');
        $date_expiration = (new DateTime('+1 hour'))->format('Y-m-d H:i:s');

        $id= $this->insert($token, $date_creation, $date_expiration);
        return $this->getById($id);
    }
}
?>
