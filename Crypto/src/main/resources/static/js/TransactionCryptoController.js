// TransactionController.js
var app = angular.module('myApp', []);

app.controller('TransactionCryptoController', ['$scope', '$http', function($scope, $http) {

    $scope.user = JSON.parse(sessionStorage.getItem('user'));
    $scope.users = [];

    $scope.getAllUser = function() {
        $http.get('http://localhost:8088/user/')
            .then(function(response) {
                $scope.users = response.data;
            })
            .catch(function(error) {
                console.error('Error loading user data:', error);
            });
    };
    $scope.getAllUser();

    $scope.getAllCrypto = function() {
        $http.get('http://localhost:8088/crypto/')
            .then(response => $scope.cryptos = response.data)
            .catch(error => console.error('Error loading cryptos:', error));
    };
    $scope.getAllCrypto();


    $scope.submitForm = function() {
        // Conversion explicite en nombre décimal
        var payload = {
            idType: $scope.formData.idType,
            quantite: parseFloat($scope.formData.quantite), // <-- Conversion critique
            date: $scope.formatDate($scope.formData.date),
            idCrypto: $scope.formData.idCrypto
        };
    
        $http.post('http://localhost:8088/user/' + $scope.idUser + '/transaction/crypto', payload)
            .then(function(response) {
                console.log(payload);
                console.log($scope.idUser);
                alert('Transaction réussie');
            })
            .catch(function(error) {
                console.log(payload);
                console.log($scope.idUser);
                if (error.status === 400) {
                    alert('Solde / cryptomonnaie insuffisant'); 
                    console.log(error);
                } else {
                    alert('Solde / cryptomonnaie insuffisant');
                    console.log(error);
                }
            });
    };

    $scope.formatDate = function(date) {
        if (!date) return '';
        var year = date.getFullYear();
        var month = ('0' + (date.getMonth() + 1)).slice(-2);
        var day = ('0' + date.getDate()).slice(-2);
        return year + '-' + month + '-' + day;
    };
}]);