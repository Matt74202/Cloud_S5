<?php
namespace App\Controller;

use App\Model\EmailModel;
use App\Model\TentativeModel;
use App\Model\InscriptionModel;
use App\Model\UserModel;
use App\Service\ResponseService;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Routing\Annotation\Route;

class TentativeController
{
    private $inscriptionModel;
    private $userModel;
    private $tentativeModel;
    private $responseService;

    public function __construct()
    {
        $this->inscriptionModel= new InscriptionModel();
        $this->userModel= new UserModel($this->inscriptionModel);
        $this->tentativeModel= new TentativeModel($this->userModel);
        $this->responseService= new ResponseService();
    }

       
    #[Route('/api/tentative/reset/{idUser}', name: 'reset_tentative')]
    public function resetTentative($idUser): JsonResponse{
        try{
            $this->tentativeModel->resetTentative($idUser);
            return $this->responseService->generateResponse('success', null, 200, 'Tentative reinitialisee avec succes.');
        }
        catch (\Exception $e) {
            return $this->responseService->generateResponse('error', null, 500, 'Erreur: ' . $e->getMessage());
        }
    }
}
?>
