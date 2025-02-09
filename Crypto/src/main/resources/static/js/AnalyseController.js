var app = angular.module('myApp', []);
app.controller('AnalyseController', ['$scope', '$http', function($scope, $http) {
    $scope.user = JSON.parse(sessionStorage.getItem('user'));
    $scope.analyse = {
        type: 1,
        dateMin: new Date(), // Use Date object instead of string
        dateMax: new Date(),
        cryptos: {},
        tous: false
    };
    
    // Load cryptos list
    $http.get('http://localhost:8088/crypto/')
        .then(function(response) {
            $scope.cryptos = response.data;
        })
        .catch(console.error);

    // Toggle all cryptos
    $scope.toggleTous = function() {
        if ($scope.analyse.tous) {
            $scope.cryptos.forEach(crypto => {
                $scope.analyse.cryptos[crypto.id] = false;
            });
        }
    };

    // Check if any crypto is selected
    $scope.checkTous = function() {
        const anySelected = $scope.cryptos.some(c => $scope.analyse.cryptos[c.id]);
        $scope.analyse.tous = !anySelected;
    };

    // Analysis submission
    $scope.analyser = function() {
        const cryptoIds = $scope.analyse.tous 
            ? [] 
            : $scope.cryptos
                .filter(c => $scope.analyse.cryptos[c.id])
                .map(c => c.id);

                $http({
                    method: 'POST',
                    url: 'http://localhost:8088/crypto/analyse',
                    data: {
                        type: $scope.analyse.type,
                        dateMin: $scope.formatDate($scope.analyse.dateMin),
                        dateMax: $scope.formatDate($scope.analyse.dateMax),
                        cryptos: cryptoIds
                    }
                }).then(response => {
            $scope.resultat = response.data;
        }).catch(console.error);
    };

    $scope.formatDate = function(date) {
        if (!date) return '';
        var year = date.getFullYear();
        var month = ('0' + (date.getMonth() + 1)).slice(-2);
        var day = ('0' + date.getDate()).slice(-2);
        return year + '-' + month + '-' + day;
    };
}]);