<?php
namespace App\Controller;

use App\Model\EmailModel;
use App\Model\InscriptionModel;
use App\Service\ResponseService;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Routing\Annotation\Route;

class InscriptionController
{
    private $emailModel;
    private $inscriptionModel;
    private $responseService;

    public function __construct()
    {
        $this->emailModel = new EmailModel();
        $this->inscriptionModel= new InscriptionModel();
        $this->responseService = new ResponseService();
    }

    #[Route('/api/inscription', name: 'demande_inscription')]
    public function inscription(Request $request): JsonResponse
    {
        try {
            $data = json_decode($request->getContent(), true);
            if (empty($data['nom']) || empty($data['email']) || empty($data['mdp'])) {
                return $this->responseService->generateResponse('error', null, 400, 'Invalid data provided.');
            }

            $idInscription= $this->inscriptionModel->insertInscription($data['nom'], $data['email'], $data['mdp']);
            $validationLink = "http://127.0.0.1:8000/api/inscription/validation/$idInscription";

            $htmlContent = file_get_contents(__DIR__ . '/templates/inscription_email.html');
            $htmlContent = str_replace('{{nom}}', $data['nom'], $htmlContent);
            $htmlContent = str_replace('{{validationLink}}', $validationLink, $htmlContent);
            $this->emailModel->sendInscriptionEmail($idInscription, $htmlContent);
            
            return $this->responseService->generateResponse('success', null, 200, 'Inscription créée. Vérifiez votre email pour valider votre inscription.');
        } 
        catch (\Exception $e) {
            return $this->responseService->generateResponse('error', null, 500, $e->getMessage());
        }
    }

}
?>
