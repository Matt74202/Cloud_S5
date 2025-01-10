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
    private $userModel;

    private string $smtpHost;
    private string $smtpUsername;
    private string $smtpPassword;
    private int $smtpPort;
    private string $smtpFromEmail;
    private string $smtpFromName;

    public function __construct() {
        $this->connection = DatabaseConnection::getConnection();
        $this->inscriptionModel = new InscriptionModel();
        $this->userModel= new UserModel($this->inscriptionModel);

        $this->smtpHost = 'smtp.gmail.com';
        $this->smtpUsername = 'naly.andriamampianina2305@gmail.com';
        $this->smtpPassword = 'wbdclyjrlnyuugdp';
        $this->smtpPort = 587;
        $this->smtpFromEmail = 'naly.andriamampianina2305@gmail.com';
        $this->smtpFromName = 'Naly Andriam';

        
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

            $mail->SMTPOptions = array(
                'ssl' => array(
                    'verify_peer' => false,
                    'verify_peer_name' => false,
                    'allow_self_signed' => true
                )
            );

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

    public function sendInscriptionEmail(int $idInscription, string $contenu): bool
    {
        try {
            $inscription= $this->inscriptionModel->getById($idInscription);

            if (!$inscription) {
                throw new \RuntimeException("Inscription avec ID $idInscription introuvable.");
            }

            return $this->send($inscription['email'], $inscription['nom'], $contenu, 'Notification pour inscription');
        } catch (PHPMailerException $e) {
            throw new \RuntimeException("Erreur lors de l'envoi de l'email : " . $e->getMessage());
        } catch (DBALException $e) {
            throw $e;
        }
    }

    public function sendEmail($idUser, $contenu) : bool
    {
        try {
            $user= $this->userModel->getById($idUser);

            if (!$user) {
                throw new \RuntimeException("User avec ID $idUser introuvable.");
            }

            return $this->send($user['email'], $user['nom'], $contenu, 'Notification pour user');
        } catch (PHPMailerException $e) {
            throw new \RuntimeException("Erreur lors de l'envoi de l'email : " . $e->getMessage());
        } catch (DBALException $e) {
            throw $e;
        }
    }

    
}
?>
