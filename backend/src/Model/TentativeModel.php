<?php
use App\Helper\DatabaseConnection;
class TentativeModel {
    private $connection;

 
    public function __construct($conn) {
        $this->connection = DatabaseConnection::getConnection();
    }

    public function addTentatives($id_user, $nombre) {
        $id_user = (int)$id_user; 
        $nombre = (int)$nombre; 

        $sql = "SELECT * FROM tentative WHERE id_user = $id_user";
        $result = $this->connection->executeQuery($sql);

        if ($result->num_rows > 0) {
            $row = $result->fetch_assoc();
            $newNombre = $row['nombre'] + $nombre;

            $updateSql = "UPDATE tentative SET nombre = $newNombre WHERE id_user = $id_user";
            $this->connection->executeQuery($updateSql);
        } else {
            // Si l'utilisateur n'existe pas, on insère une nouvelle ligne pour l'utilisateur
            $insertSql = "INSERT INTO tentative (id_user, nombre) VALUES ($id_user, $nombre)";
            $this->connection->executeQuery($insertSql);
        }
    }
}
?>
