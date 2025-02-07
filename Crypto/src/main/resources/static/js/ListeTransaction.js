// TransactionController.js
var app = angular.module('myApp', []);

app.controller('ListeTransactionController', ['$scope', '$http', function($scope, $http) {

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
        $http.get('http://localhost:8088/transaction/crypto/')
            .then(function(response) {
                $scope.transactions = response.data;
            })
            .catch(function(error) {
                console.error('Error loading transactions data:', error);
            });
    };
    $scope.getAllTransactions();


}]);