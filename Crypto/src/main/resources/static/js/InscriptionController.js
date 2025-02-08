var app = angular.module('myApp', []);

app.controller('InscriptionController', ['$scope', '$http', function($scope, $http) {
    $scope.formData = {};

    $scope.submitForm = function() {
        // First API call with credentials
        $http.post('http://localhost:8000/api/inscription', $scope.formData, { withCredentials: true })
            .then(function(response) {
                if (response.data.status === 'success') {
                    alert('Vérifiez votre mail pour confirmer votre inscription');

                    $http.post('http://localhost:8088/user/inscription', $scope.formData, { withCredentials: true });
                } else {
                    alert('Erreur lors de l\'inscription');
                }
            })
            .catch(function() {
                alert('Erreur lors de l\'inscription.');
            });
    };
}]);
