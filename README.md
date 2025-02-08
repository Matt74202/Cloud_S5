Membres:
    - RAMEFISON Matthew Herintsoa, ETU002774
    - ANDRIAMAMPIANINA Naly Malala Fitiavana, ETU001749

---------------------------------------------------------------------------------------------------------------------------------------------
I- FOURNISSEUR D'IDENTITE (code dans backend)
    1- Installer les images docker avec 'docker-compose up --build'
    2- La base aura comme user:'cloud', mot de passe:'cloud', database:'cloud'. 
       Les details sur les tables se trouvent dans backend/sql/base.sql
    3- Installer les dependances dans vendor si necessaire avec:   
        . docker exec -it backend-app-1 bash
        . composer install
    4- Email de test: 
        email: "usercloudexemple@gmail.com"
        mdp: "testcloud2025"

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

------------------------------------------------------------------------------------------------------------------------------------------------
