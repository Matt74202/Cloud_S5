<?php
namespace App\Model;

use \DateTime;
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
        $expiration = date('Y-m-d H:i:s', strtotime('+5 minute')); 
        $query= "INSERT INTO Pin (id_utilisateur, pin, date_creation, date_expiration) values(:idUser, :pin, :creation, :expiration)";
        $stmt = $this->connection->executeQuery($query, ['idUser' =>$idUser, 'pin'=>$pin , 'creation'=> $creation, 'expiration'=> $expiration]);
        return $pin;
    }

    public function getByPin($pin){
        $query = "SELECT * FROM Pin WHERE pin = :pin AND date_expiration > :current_date";
        $currentDate= (new DateTime())->format('Y-m-d H:i:s');
        $stmt = $this->connection->executeQuery($query, [
            'pin' => $pin,
            'current_date' => (new DateTime())->format('Y-m-d H:i:s')
        ]);
        return $stmt->fetchAssociative();
    }
    
    public function isPinValid ($idUser, $pin){
        $pin= $this->getByPin($pin);
        if($pin){
            if ($pin['id_utilisateur']==$idUser){
                return true;
            }
        }
        return false;
    }


}    
?>    