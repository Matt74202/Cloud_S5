<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inscription</title>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.8.2/angular.min.js"></script>
    <script src="assets/js/app.js"> </script>
<!-- Lien vers Bootstrap CDN sans l'attribut 'integrity' -->
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <style>
        body {
            background-color: #000;
            color: #fff;
            font-family: Arial, sans-serif;
            padding: 50px;
        }

        .login-container {
            max-width: 400px;
            margin: 0 auto;
            background-color: #222;
            padding: 30px;
            border-radius: 10px;
            box-shadow: 0px 0px 20px 0px rgba(255,255,255,0.2); /* Ombre subtile */
        }

        .form-group label {
            color: #fff;
        }

        .form-control {
            background-color: #333;
            color: #fff;
            border: 1px solid #555;
        }

        .btn-primary {
            background-color: #fff;
            color: #000;
            border: none;
            padding: 10px 20px;
            font-weight: bold;
            transition: background-color 0.3s ease;
        }

        .btn-primary:hover {
            background-color: #ccc;
            color: #000;
        }
    </style>
</head>
<body ng-app="myApp" ng-controller="InscriptionController">

    <div class="container">
        <div class="login-container">
            <h2 class="text-center mb-4">Inscription</h2>
            <form ng-submit="submitForm()">
                <div class="mb-3 form-group">
                    <label for="email" class="form-label">Nom</label>
                    <input type="text" class="form-control" id="nom" ng-model="formData.nom" required>
                </div>
                <div class="mb-3 form-group">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" id="email" ng-model="formData.email" required>
                </div>
                <div class="mb-3 form-group">
                    <label for="password" class="form-label">Mot de passe</label>
                    <input type="password" class="form-control" id="password" ng-model="formData.mdp" required>
                </div>
                <button type="submit" class="btn btn-primary btn-block">S'inscrire</button>
            </form>
        </div>
    </div>

    <!-- Script Bootstrap -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pzjw8f+ua7Kw1TIq0v8FqCk+8iXy2+7OoUqOe39syzz8M0dDkhkCXdoN+cdE+QdR" crossorigin="anonymous"></script>
</body>
</html>
