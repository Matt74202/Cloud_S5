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