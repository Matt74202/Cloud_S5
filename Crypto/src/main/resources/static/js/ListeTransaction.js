// TransactionController.js
var app = angular.module('myApp', []);

app.controller('ListeTransactionController', ['$scope', '$http', function($scope, $http) {

    $scope.user = JSON.parse(sessionStorage.getItem('user'));

    $scope.formData = {
        idCrypto: 0, // default value
        idType: 0,   // default value
        idUtilisateur: 0 // default value
    };
    
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

    $scope.getAllUsers = function() {
        $http.get('http://localhost:8088/user/')
            .then(function(response) {
                $scope.users = response.data;
            })
            .catch(function(error) {
                console.error('Error loading users data:', error);
            });
    };
    $scope.getAllUsers(); 

    $scope.getAllTransactions = function() {
        var params = {
            idCrypto: $scope.formData.idCrypto || 0, // Use 0 or another default if not selected
            idType: $scope.formData.idType || 0,     // Default to 0 if not selected
            idUtilisateur: $scope.formData.idUtilisateur || 0 // Default to 0 if not selected
        };
    
        $http.get('http://localhost:8088/transaction/crypto/filtre', { params: params })
            .then(function(response) {
                console.log($scope.formData);
                $scope.transactions = response.data;
            })
            .catch(function(error) {
                console.error('Error loading transactions data:', error);
            });
    };
    $scope.getAllTransactions();
    




}]);