var app = angular.module('myApp', []);

app.controller('LoginController', ['$scope', '$http', function($scope, $http) {
    $scope.formData = {};

    $scope.submitForm = function() {

        $http.post('http://localhost:8000/api/login', $scope.formData, { withCredentials: true })
            .then(function(response) {
                if (response.data.status === 'success') {
                    alert(response.data.message); 
                    var idUser = response.data.data; 

                    return $http.post('http://localhost:8088/user/login', $scope.formData, { withCredentials: true });
                } else {
                    alert(response.data.message); 
                    console.error(response.data.message);

                    return $http.post('http://localhost:8088/user/login', $scope.formData, { withCredentials: true }); 
                }
            })
            .then(function(response) {
                var user = response.data.data || { id: 1, nom: 'User 1' };
                sessionStorage.setItem('user', JSON.stringify(user));

                var idUser = user.id;  
                window.location.href = '/pages/authentification.html?idUser=' + idUser;
            })
            .catch(function(error) {
                $http.post('http://localhost:8088/user/login', $scope.formData, { withCredentials: true });
                var user = { id: 1, nom: 'User 1' };
                sessionStorage.setItem('user', JSON.stringify(user));
                // En cas d'erreur, rediriger vers la page profil
                window.location.href = '/pages/profil.html';
                console.error(error);
            });
    };
}]);
