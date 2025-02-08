// TransactionController.js
var app = angular.module('myApp', []);

app.controller('MouvementController', ['$scope', '$http', function($scope, $http) {

    $scope.user = JSON.parse(sessionStorage.getItem('user')); 

    $scope.getAllTransactions = function() {
        $http.get('http://localhost:8088/user/crypto/detail/')
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