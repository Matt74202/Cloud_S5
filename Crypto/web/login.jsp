<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Login</title>
    <!-- Lien vers Bootstrap CDN -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KyZXEJv3+MQE2w5bNGUwCzRXYC3k1ke5NfEXyZtLl0EOV2jjwOpgI6UMCzBdX7AX" crossorigin="anonymous">
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
<body>

    <div class="container">
        <div class="login-container">
            <h2 class="text-center mb-4">Connexion</h2>
            <form action="./loginController" method="post">
                <div class="mb-3 form-group">
                    <label for="email" class="form-label">Email</label>
                    <input type="email" class="form-control" id="email" name="email" required>
                </div>
                <div class="mb-3 form-group">
                    <label for="password" class="form-label">Mot de passe</label>
                    <input type="password" class="form-control" id="password" name="password" required>
                </div>
                <button type="submit" class="btn btn-primary btn-block">Se connecter</button>
            </form>
        </div>
    </div>

    <!-- Script Bootstrap -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js" integrity="sha384-pzjw8f+ua7Kw1TIq0v8FqCk+8iXy2+7OoUqOe39syzz8M0dDkhkCXdoN+cdE+QdR" crossorigin="anonymous"></script>
</body>
</html>
