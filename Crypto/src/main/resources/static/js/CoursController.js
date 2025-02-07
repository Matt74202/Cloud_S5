// TransactionController.js
var app = angular.module('myApp', []);

app.controller('CoursController', ['$scope', '$http', function($scope, $http) {
    $scope.formData = {
        date: new Date() // Initialiser avec la date actuelle si nécessaire
    };
    $scope.user = JSON.parse(sessionStorage.getItem('user'));

    $scope.getAllCrypto = function() {
        $http.get('http://localhost:8088/crypto/')
            .then(function(response) {
                $scope.cryptos = response.data;
            })
            .catch(function(error) {
                console.error('Error loading cryptos data:', error);
            });
    };
    $scope.getAllCrypto();


    $scope.generateCryptos = function() {
        $http.post('http://localhost:8088/crypto/generer')
            .then(function(response) {
                console.log('Cryptos generated:', response.data);
                $scope.getAllCrypto();
            })
            .catch(function(error) {
                console.error('Error generating cryptos:', error);
            });
    };
    setInterval(function() {
        $scope.generateCryptos();
    }, 10000); 


}]);