var app = angular.module('myApp', []);

app.controller('InscriptionController', ['$scope', '$http', function($scope, $http) {
    $scope.formData = {};

    $scope.submitForm = function() {

        $http.post('http://localhost:8000/api/inscription', $scope.formData)
            .then(function(response) {
                alert('Verifiez votre mail pour confirmer votre inscription');
                console.log(response.data);
            })
            .catch(function(error) {
                alert('Erreur lors de l\'inscription.');
                console.error(error);
            });
    };
}]);
