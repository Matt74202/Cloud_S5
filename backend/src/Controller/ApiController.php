<?php
// backend/src/Controller/ApiController.php
namespace App\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Component\Routing\Annotation\Route;

class ApiController extends AbstractController
{
    #[Route('/api/data', name: 'api_data')]
    public function getData(): JsonResponse
    {
        return $this->json(['message' => 'Manaona!']);
    }
}
?>