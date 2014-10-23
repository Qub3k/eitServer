(function() {
	var app = angular.module('server', ['ngRoute']);

	app.controller('ListCtrl', function ($scope, $http) {
		// Gettin all the files on the server and saving them in the variable 'files'
    	$http.get('/fileList').success(function (data) {
    	    $scope.files = data;
    	}).error(function (data, status) {
    	    console.log('Error ' + data)
    	})
    });
}) ();