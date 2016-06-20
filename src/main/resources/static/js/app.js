var app = angular.module("app",
    ['ngRoute', 'app.services', 'app.controllers', 'app.directives']).config(
    function ($routeProvider) {

        var baseUrl = "html/"

        $routeProvider.when('/', {
            redirectTo: '/chart',
            controller: 'ChartController'
        })
            .when('/chart', {
                templateUrl: baseUrl + 'chart.html',
                controller: 'ChartController'
            })
            .when('/configuration', {
                templateUrl: baseUrl + 'configuration.html',
                controller: 'ConfigurationController'
            })
            .otherwise({
                redirectTo: '/',
                controller: 'ChartController',
            });
    });
