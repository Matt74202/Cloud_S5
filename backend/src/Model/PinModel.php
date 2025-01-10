<?php
namespace App\Model;

use App\Helper\DatabaseConnection;
class PinModel {
    private $connection;

 
    public function __construct() {
        $this->connection = DatabaseConnection::getConnection();
    }

    function genererPinAleatoire() {
        return str_pad(random_int(0, 999999), 6, '0', STR_PAD_LEFT);
    }

    function insertPin($idUser){
        $pin= $this->genererPinAleatoire();
        $creation = date('Y-m-d H:i:s');
        $expiration = date('Y-m-d H:i:s', strtotime('+1 minute')); 
        $query= "INSERT INTO Pin (id_utilisateur, pin, date_expiration, date_creation) values(:idUser, :pin, :creation, :expiration)";
        $stmt = $this->connection->executeQuery($query, ['idUser' =>$idUser, 'pin'=>$pin , 'creation'=> $creation, 'expiration'=> $expiration]);
        return $pin;
    }

    // function getByPin($mysqli, $pin) {
    //     $pin = $mysqli->real_escape_string($pin);
    //     $sql = "SELECT * FROM Pins WHERE pin = '$pin'";
    //     $result = $mysqli->query($sql);
    //     if ($result->num_rows > 0) {
    //         $data = $result->fetch_assoc();
    //         $current_time = date('Y-m-d H:i:s');
    //         if ($current_time <= $data['date_expiration']) {
    //             echo "PIN valide pour l'utilisateur : " . $data['id_user'] . "\n";
    //             return $data;
    //         } else {
    //             echo "Le PIN a expiré.\n";
    //             return null;
    //         }
    //     } else {
    //         echo "PIN incorrect.\n";
    //         return null;
    //     }
    // }

    public function getByPin ($pin): array{
        $query = "SELECT * FROM Pin WHERE pin = :pin AND date_expiration > ?";
        $stmt = $this->connection->executeQuery($query, ['pin' => $pin, new DateTime()]);
        return $stmt->fetchAssociative(); 
    }




}    
?>    