var app = angular.module('myApp', []);

app.controller('EvolutionController', ['$scope', '$http', function($scope, $http) {
    $scope.user = JSON.parse(sessionStorage.getItem('user'));
    $scope.chartVisible = false; 
    window.currentChart = null; 

    $scope.getAllCrypto = function() {
        $http.get('http://localhost:8088/crypto/')
            .then(response => $scope.cryptos = response.data)
            .catch(error => console.error('Error loading cryptos:', error));
    };
    $scope.getAllCrypto();

    $scope.showChart = function() {
        if (!$scope.idCrypto) {
            alert("Veuillez sélectionner une crypto !");
            return;
        }

        if (window.currentChart) {
            window.currentChart.destroy();
            $scope.chartVisible = false;
        }

        $http.get(`http://localhost:8088/crypto/${$scope.idCrypto}/historique`)
            .then(response => {
                $scope.historique = response.data;
                $scope.renderChart();
                $scope.chartVisible = true;
            })
            .catch(error => {
                alert("Erreur lors du chargement de l'historique !");
                console.error(error);
            });
    };

    $scope.renderChart = function() {
        const ctx = document.getElementById('crypto-chart').getContext('2d');
        const labels = $scope.historique.map(item => 
            new Date(item.date).toLocaleString() // Date + heure
        );
        const data = $scope.historique.map(item => item.valeur);

        window.currentChart = new Chart(ctx, {
            type: 'line',
            data: {
                labels: labels,
                datasets: [{
                    label: 'Évolution du prix',
                    data: data,
                    borderColor: '#FFD700', // Jaune doré
                    backgroundColor: 'rgba(255, 215, 0, 0.1)', // Jaune semi-transparent
                    tension: 0.4,
                    borderWidth: 2,
                    pointBackgroundColor: '#FFD700' // Couleur des points
                }]
            },
            options: {
                responsive: true,
                plugins: {
                    title: {
                        display: true,
                        text: 'Historique des valeurs',
                        color: '#666' // Couleur du titre
                    }
                },
                scales: {
                    y: { 
                        beginAtZero: false,
                        grid: { color: 'rgba(200, 200, 200, 0.2)' } 
                    },
                    x: { 
                        grid: { color: 'rgba(200, 200, 200, 0.2)' } 
                    }
                }
            }
        });
    };
}]);