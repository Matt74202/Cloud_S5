<?php
namespace App\Model;

use Doctrine\DBAL\Connection;
use Doctrine\DBAL\Exception as DBALException; 
use PHPMailer\PHPMailer\PHPMailer;
use PHPMailer\PHPMailer\Exception as PHPMailerException;
use App\Helper\DatabaseConnection;

class EmailModel
{
    private $connection;
    private $inscriptionModel;

    private string $smtpHost;
    private string $smtpUsername;
    private string $smtpPassword;
    private int $smtpPort;
    private string $smtpFromEmail;
    private string $smtpFromName;

    public function __construct() {
        $this->connection = DatabaseConnection::getConnection();
        $this->inscriptionModel = new InscriptionModel();

        $this->smtpHost = 'smtp.gmail.com';
        $this->smtpUsername = 'naly.andriamampianina2305@gmail.com';
        $this->smtpPassword = 'vbaxuguvnyvmqkde';
        $this->smtpPort = 587;
        $this->smtpFromEmail = 'naly.andriamampianina2305@gmail.com';
        $this->smtpFromName = 'Nom de l\'Expéditeur';

        
    }

    private function send(string $email, string $nom, string $contenu, string $subject): bool
    {
        try {
            $mail = new PHPMailer(true);

            $mail->isSMTP();
            $mail->Host = $this->smtpHost;
            $mail->SMTPAuth = true;
            $mail->Username = $this->smtpUsername;
            $mail->Password = $this->smtpPassword;
            $mail->SMTPSecure = PHPMailer::ENCRYPTION_STARTTLS;
            $mail->Port = $this->smtpPort;

            $mail->setFrom($this->smtpFromEmail, $this->smtpFromName);
            $mail->addAddress($email, $nom); 
            $mail->isHTML(true);
            $mail->Subject = $subject;
            $mail->Body = $contenu;

            $mail->send();

            return true;
        } catch (PHPMailerException $e) {
            throw new \RuntimeException("Erreur lors de l'envoi de l'email : " . $e->getMessage());
        }
    }

    
}
?>
