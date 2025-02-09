// TransactionController.js
var app = angular.module('myApp', []);

app.controller('CommissionController', ['$scope', '$http', function($scope, $http) {

    $scope.user = JSON.parse(sessionStorage.getItem('user'));

    $scope.submitForm = function() {
  
        $http.post('http://localhost:8088/commission/', $scope.formData)
            .then(function(response) {
                alert('Modification réussie');
            })
            .catch(function(error) {
                alert('Erreur lors de la modification');
            });
    };
    




}]);