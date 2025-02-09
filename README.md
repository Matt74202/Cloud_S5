# Projet final - Achat/Vente de cryptomonnaie
 ----------------------------------------------------------------------------------------------------------------------------------------------

## Membres:
    - RAMEFISON Matthew Herintsoa, ETU002774
    - ANDRIAMAMPIANINA Naly Malala Fitiavana, ETU001749

-----------------------------------------------------------------------------------------------------------------------------------------------

## TECHNOLOGIES UTILISEES
    -> Fournisseur d'identite: 
        - backend: Symfony 
        - base: Postgresql
    
    -> Web:
        - backend: Spring-boot
        - front-end: html & angularJS
        - base: Postgresql

    -> Mobile:
        - front-end: React-Native

    -> Service cloud:
        - firebase (notifications)

-----------------------------------------------------------------------------------------------------------------------------------------------

## FOURNISSEUR D'IDENTITE (code source dans backend)
    - Installation du conteneur: 
        . docker-compose up --build

    - Installation des dependances dans vendor (si necessaire)
        . docker exec -it backend-app-1 bash
        . composer install

    - La base aura comme user:'cloud', mot de passe:'cloud', database:'cloud'. 
       Les details sur les tables se trouvent dans backend/sql/base.sql
        
    - Email de test: 
        email: "usercloudexemple@gmail.com"
        mdp: "testcloud2025"

    -> ROUTES POSTMAN :
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


-------------------------------------------------------------------------------------------------------------------------------------------------
## WEB CRYPTOMONNAIE (code source dans Crypto)
    - Installation du conteneur: 
        ``` bash 
        docker-compose up --build 
        ```

    - La base aura comme user:'cloud', mot de passe:'cloud', database:'cloud'. 
      Les details sur la base de donnees se trouvent dans crypto/sql
      Le mcd final sous forme de pdf se trouve dans mcd.pdf

    - Lancer le projet avec http://localhost:8088 pour atteindre la page de connexion. 

    - Le profil utilise par defaut le compte de l'User 1 



