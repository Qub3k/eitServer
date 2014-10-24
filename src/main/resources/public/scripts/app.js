(function() {
	var app = angular.module('server', ['ngRoute']);

	app.config(['$routeProvider', '$locationProvider', function($routeProvider, $locationProvider) {
    	$routeProvider.when('/', {
    		controller: 'ListCtrl',
    		controllerAs: 'list',
    		templateUrl: '/views/files.html'
    	})
    	.when('/directory/:directoryPath*', {
    		controller: 'DirContentCtrl',
    		controllerAs: 'dir',
    		templateUrl: '/views/files.html'
    	});

    	$locationProvider.html5Mode(true);
    }]);

	app.controller('ListCtrl', function ($scope, $http) {
		// Gettin all the files on the server and saving them in the variable 'files'
		$scope.files = [];
		$scope.files.length = 0;
    	$http.get('/mainFiles').success(function (data) {
    	    $scope.files = data;
    	}).error(function (data, status) {
    	    console.log('Error ' + data)
    	})
    });

	app.controller('DirContentCtrl', ['$scope', '$http', '$routeParams', function($scope, $http, $routeParams) {
		$scope.files = [];
		$scope.files.length = 0;
		$http.get('/directory/' + $routeParams.directoryPath)
			.success(function (data) {
				$scope.files = data;
			})
			.error(function (data, status) {
				console.log('Error ' + data);
			})
	}]);
}) ();