'use strict';

/* App Module */

var springChat = angular.module('springChat', ['springChat.controllers',
                                               'springChat.services',
                                               'springChat.directives','ui.router','ngResource'])
.controller('MainController',['$state','$location','$scope', function($state,$location,$scope){

	$scope.login= function(){
		$state.go('chat');
	}

	$scope.userName="";
	$scope.password="";

}])
.config(['$stateProvider', '$urlRouterProvider','$locationProvider', function ($stateProvider, $urlRouterProvider,$locationProvider) {

        
        /*$locationProvider.html5Mode({
			  enabled: true,
			  requireBase: false
		});*/
        $urlRouterProvider.otherwise("/login");
        
        $stateProvider
            .state("login", {
                url: '/login',
                templateUrl: "login.html"

            })
            .state("chat", {
                url: '/chat',
                templateUrl: "chat.html"

            })
            
            
    }])
;