<?php
use App\Helper\DatabaseConnection;
class TokenModel
{
    private $connection;

    public function __construct()
    {
        $this->connection = DatabaseConnection::getConnection();
    }

    public function insert(string $token,$date_creation,$date_expiration): int
    {
        try {
            $query = "INSERT INTO token (token, date_creation, date_expiration) VALUES (:token, :date_creation, :date_expiration)";
            $stmt = $this->connection->executeQuery($query, ['token' => $token['token'], 'date_creation'=>$date_creation , 'date_expiration'=> $date_expiration)];
            return (int)$this->connection->lastInsertId(); 
        } catch (Exception $e) {
            throw $e;
        }
    }

    public function getById(int $id): ?array
    {
        try {
            $query = "SELECT * FROM token WHERE id = :id";
            $stmt= $this->connection->executeQuery($query,['id' =>$id]); 
                return $stmt->fetchAssociative(); 
        } catch (Exception $e) {
            throw $e;
        }
    }
    public function generateToken(int $idUser): int
    {
        try {

            $token = bin2hex(random_bytes(32));
            $date_creation = new DateTime();
            $date_expiration = new DateTime('+1 hour');

            return $this->insert($token, $date_creation, $date_expiration);
        } catch (Exception $e) {
            throw $e;
        }
    }
}
