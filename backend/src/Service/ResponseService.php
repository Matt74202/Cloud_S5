<?php 
namespace App\Service;

use Symfony\Component\HttpFoundation\JsonResponse;

class ResponseService
{
    public function generateResponse(string $status, $data = null, int $code = 200, string $message = '', array $details = []): JsonResponse
    {
        // Si le status est 'success', on définit 'error' à null
        $error = $status === 'success' ? null : [
            'code' => $code,
            'message' => $message,
            'details' => $details
        ];

        $response = [
            'status' => $status,
            'data' => $data,
            'error' => $error
        ];

        return new JsonResponse($response, $code);
    }

}

?>