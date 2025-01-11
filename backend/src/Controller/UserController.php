<?php
namespace App\Controller;

use App\Model\EmailModel;
use App\Model\InscriptionModel;
use App\Model\TentativeModel;
use App\Model\UserModel;
use App\Model\PinModel;
use App\Model\TokenModel;
use App\Service\ResponseService;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Routing\Annotation\Route;

class UserController
{
    private $emailModel;
    private $inscriptionModel;
    private $responseService;
    private $userModel;
    private $tentativeModel;
    private $pinModel;
    private $tokenModel;

    public function __construct()
    {
        $this->emailModel = new EmailModel();
        $this->inscriptionModel= new InscriptionModel();
        $this->userModel= new UserModel($this->inscriptionModel);
        $this->responseService = new ResponseService();
        $this->tentativeModel= new TentativeModel($this->userModel);
        $this->pinModel = new PinModel();
        $this->tokenModel = new TokenModel();
    }

    #[Route('/api/login', name: 'login')]
    public function login(Request $request): JsonResponse{
        try{
            $data = json_decode($request->getContent(), true);
            if (empty($data['email']) || empty($data['mdp'])) {
                return $this->responseService->generateResponse('error', null, 400, 'Veuillez remplir tous les champs');
            }

            $email= $data['email'];
            $mdp= $data['mdp'] ;
            $user= $this->userModel->login($email, $mdp);
            
            if (!$user){
                return $this->responseService->generateResponse('error', null, 500, 'Email incorrect');
            }
            $idUser= $user['id'];

            
            if ($user['email']==null){              //mdp incorrect
                //return $this->responseService->generateResponse('error', null, 500, 'Mdp incorrect');

                $validationLink = "http://localhost:8000/api/tentative/reset/$idUser";
                $htmlContent = file_get_contents(__DIR__ . '/../templates/tentative.html');
                $htmlContent = str_replace('{{validationLink}}', $validationLink, $htmlContent);
    
                $check= $this->tentativeModel->checkTentative($idUser, $htmlContent);

                if ($check){
                    return $this->responseService->generateResponse('error', null, 500, 'Mot de passe incorrect');
                }
                else {
                    return $this->responseService->generateResponse('error', null, 500, 'Nombre de tentatives depasse, veuillez verifier votre email pour reinitialiser');
                }
            }
            else {
                $pin= $this->pinModel->insertPin($idUser);
                $pinArray= str_split($pin);
                
                $validationLink = "http://localhost:8000/api/login/$idUser/$pin";
                $htmlContent = file_get_contents(__DIR__ . '/../templates/pin.html');
                $htmlContent = str_replace('{{pin}}', $pin, $htmlContent);
                $htmlContent = str_replace('{{validationLink}}', $validationLink, $htmlContent);
                $this->emailModel->sendEmail($idUser, $htmlContent);
                
                return $this->responseService->generateResponse('success', 'Un pin a ete envoye a votre email', 200, 'Un pin a ete envoye a votre email');
            }
            
        }
        catch (\Exception $e) {
            return $this->responseService->generateResponse('error', null, 500, $e->getMessage());
        }
    }
    
    #[Route('/api/login/{idUser}/{pin}', name: 'login_confirmer')]
    public function confirmLogin($idUser, $pin): JsonResponse{
        try{
            $pin= $this->pinModel->isPinValid($idUser, $pin);
            if ($pin){
                $token= $this->tokenModel->generateToken($idUser);
                return $this->responseService->generateResponse('success', $token, 200, 'Pin correct, login valide');
            } 
            else{
                $this->tentativeModel->addTentative($idUser);
                return $this->responseService->generateResponse('error', null, 500, 'Pin incorrect ou invalide');
            } 
        }
        catch (\Exception $e) {
            return $this->responseService->generateResponse('error', null, 500, 'Erreur: ' . $e->getMessage());
        }
    }

    #[Route('/api/user/update/{idUser}', name: 'login_confirmer')]
    public function updateData($idUser, Request $request): JsonResponse{
        try{
            $data = json_decode($request->getContent(), true);
            if (empty($data['nom']) || empty($data['mdp'])) {
                return $this->responseService->generateResponse('error', null, 400, 'Veuillez remplir tous les champs.');
            }

            $update= $this->userModel->updateUser($idUser, $data['nom'], $data['mdp']);
            if ($update){
                return $this->responseService->generateResponse('success', null, 200, 'Informations modifiees');
            }
            return $this->responseService->generateResponse('error', null, 500, 'Erreur lors de la modification des informations');
        }
        catch (\Exception $e) {
            return $this->responseService->generateResponse('error', null, 500, 'Erreur: ' . $e->getMessage());
        }
    }

}
?>
