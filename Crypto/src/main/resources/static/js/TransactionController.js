// TransactionController.js
var app = angular.module('myApp', []);

app.controller('TransactionController', ['$scope', '$http', function($scope, $http) {
    $scope.formData = {
        date: new Date() // Initialiser avec la date actuelle si nécessaire
    };
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

    $scope.submitForm = function() {
        // Conversion explicite en nombre décimal
        var payload = {
            idType: $scope.formData.idType,
            montant: parseFloat($scope.formData.montant), // <-- Conversion critique
            date: $scope.formatDate($scope.formData.date)
        };
    
        $http.post('http://localhost:8088/user/' + $scope.idUser + '/transaction/fond', payload)
            .then(function(response) {
                alert('Transaction réussie');
            })
            .catch(function(error) {
                if (error.status === 400) {
                    alert('Solde insuffisant'); 
                } else {
                    alert('Solde insuffisant');
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