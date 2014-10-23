(function() {
	var app = angular.module('server', ['ngRoute']);

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

    app.config(['$routeProvider', function($routeProvider) {
    	$routeProvider.when('/', {
    		controller: 'ListCtrl',
    		controllerAs: 'list',
    		templateUrl: 'views/files.html'
    	})
    	.when('/:dir', {
    		controller: 'ListCtrl',
    		controllerAs: 'list',
    		templateUrl: 'views/files.html'
    	});
    }]);

}) ();