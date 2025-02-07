var app = angular.module('myApp', []);

app.controller('ProfilController', ['$scope', '$http', function($scope, $http) {
    $scope.formData = {};
    $scope.user = JSON.parse(sessionStorage.getItem('user'));
    $scope.cryptos = [];
    $scope.portefeuilles = [];
    $scope.filteredPortefeuilles = []; // Initialized here
    $scope.selectedCrypto = 0; 

    $scope.loadFond = function() {
        $http.get('http://localhost:8088/user/' + $scope.user.id + '/fond')
            .then(function(response) {
                $scope.fond = response.data;
            })
            .catch(function(error) {
                console.error('Error loading fond data:', error);
            });
    };

    $scope.loadCryptos = function() {
        $http.get('http://localhost:8088/cryptos')
            .then(function(response) {
                $scope.cryptos = response.data;
            })
            .catch(function(error) {
                console.error('Error fetching cryptos:', error);
            });
    };

    $scope.loadPortefeuilles = function() {
        $http.get('http://localhost:8088/user/' + $scope.user.id + '/portefeuille')
            .then(function(response) {
                $scope.portefeuilles = response.data;
                $scope.filterCrypto(); // Initialize filteredPortefeuilles after data loads
            })
            .catch(function(error) {
                console.error('Error loading portefeuille data:', error);
            });
    };

    $scope.filterCrypto = function() {
        if ($scope.selectedCrypto === 0) {
            $scope.filteredPortefeuilles = $scope.portefeuilles;
        } else {
            $scope.filteredPortefeuilles = $scope.portefeuilles.filter(function(portefeuille) {
                return portefeuille.crypto.id === $scope.selectedCrypto;
            });
        }
    };

    $scope.loadFond();
    $scope.loadCryptos();
    $scope.loadPortefeuilles();
}]);