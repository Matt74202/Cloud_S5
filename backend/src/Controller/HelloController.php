<?php

namespace App\Controller;

use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;

class HelloController
{
    #[Route('/hello', name: 'hello_page')]
    public function hello(): Response
    {
        return new Response('<h1>Bonjour, Symfony !</h1>');
    }
}
