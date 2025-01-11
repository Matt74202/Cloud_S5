# Cloud_S5

ROUTES POSTMAN :
    - Inscription
        http://localhost:8000/api/inscription
    - Valider inscription   
        Lien envoye dans le mail ou http://localhost:8000/api/inscription/validation/{idInscription}
    - Login
        http://localhost:8000/api/login 
    - Confirmer login - validation avec pin
        Lien envoye dans le mail ou http://localhost:8000/api/login/{idUser}/{pin}
    - Reinitialiser les tentatives
        Lien envoye dans le mail ou http://localhost:8000/api/tentative/reset/{idUser}
    - Gestion du compte - Modification des informations
        http://localhost:8000/api/user/update/{idUser}

