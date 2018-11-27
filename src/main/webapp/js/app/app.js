'use strict';

var app = angular.module('anti-criminal',
		['anti-criminal.controllers', 'anti-criminal.services', 'ngRoute', 'ngCookies']);

app.config(['$httpProvider','$routeProvider', function($httpProvider,$routeProvider){
	//$routeProvider.when("/",{templateUrl: "home.html", controller:'FrontPageCtrl'});

}]);
