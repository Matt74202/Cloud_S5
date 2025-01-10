<?php
use App\Helper\DatabaseConnection;
class PinModel {
    private $connection;

 
    public function __construct($conn) {
        $this->connection = DatabaseConnection::getConnection();
    }

    function genererPinAleatoire($id_user) {
        $pin = str_pad(random_int(0, 999999), 6, '0', STR_PAD_LEFT);
        return [
            'id_user' => $id_user,
            'pin' => $pin
        ];
    }
    
    function insererPin($mysqli, $id_user) {
        $data = $this->genererPinAleatoire($id_user);
        $pin = $data['pin'];
        $date_creation = date('Y-m-d H:i:s');
        $date_expiration = date('Y-m-d H:i:s', strtotime('+1 minute')); 
        $sql = "INSERT INTO Pins (id_user, pin, date_creation, date_expiration) 
                VALUES ('$id_user', '$pin', '$date_creation', '$date_expiration')";
        if ($mysqli->query($sql) === TRUE) {
            echo "PIN généré et inséré avec succès pour l'utilisateur $id_user : $pin\n";
        } else {
            echo "Erreur lors de l'insertion du PIN : " . $mysqli->error . "\n";
        }
    }

    function getByPin($mysqli, $pin) {
        $pin = $mysqli->real_escape_string($pin);
        $sql = "SELECT * FROM Pins WHERE pin = '$pin'";
        $result = $mysqli->query($sql);
        if ($result->num_rows > 0) {
            $data = $result->fetch_assoc();
            $current_time = date('Y-m-d H:i:s');
            if ($current_time <= $data['date_expiration']) {
                echo "PIN valide pour l'utilisateur : " . $data['id_user'] . "\n";
                return $data;
            } else {
                echo "Le PIN a expiré.\n";
                return null;
            }
        } else {
            echo "PIN incorrect.\n";
            return null;
        }
    }



}    
?>    