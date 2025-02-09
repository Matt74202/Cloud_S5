var app = angular.module('myApp', []);

app.controller('InscriptionController', ['$scope', '$http', function($scope, $http) {
    $scope.formData = {};

    $scope.submitForm = function() {

        $http.post('http://localhost:8000/api/inscription', $scope.formData)
            .then(function(response) {
                if (response.data.status === 'success') {
                    alert('Verifiez votre mail pour confirmer votre inscription');
                } else {
                    alert('Erreur lors de l\'inscription');
                    console.error(response.data.message);
                }
            })
            .catch(function(error) {
                alert('Erreur lors de l\'inscription.');
                console.error(error);
            });
    };
}]);

app.controller('LoginController', ['$scope', '$http', function($scope, $http) {
    $scope.formData = {};

    $scope.submitForm = function() {

        $http.post('http://localhost:8000/api/login', $scope.formData)
            .then(function(response) {
                if (response.data.status === 'success') {
                    // alert(response.data.message);
                    var idUser= response.data.data;
                    window.location.href = 'pages/authentification.jsp?idUser=' + idUser;
                } else {
                    alert(response.data.message);
                    console.error(response.data.message);
                }
            })
            .catch(function(error) {
                alert('Erreur lors de l\'inscription.');
                console.error(error);
            });
    };
}]);

app.controller('AuthController', ['$scope', '$http', function($scope, $http) {
    $scope.formData = {};

    var urlParams = new URLSearchParams(window.location.search);
    var idUser = urlParams.get('idUser'); 

    $scope.submitForm = function() {

        // Effectuer une requête GET vers l'API avec idUser et pin
        $http.get('http://localhost:8000/api/login/' + idUser + '/' + $scope.formData.pin)
            .then(function(response) {
                if (response.data.status === 'success') {
                    alert(response.data.message); // Message de succès
                } else {
                    alert(response.data.message); // Message d'erreur
                    console.error(response.data.message);
                }
            })
            .catch(function(error) {
                alert('Erreur lors de la validation.');
                console.error(error);
            });
    };
}]);