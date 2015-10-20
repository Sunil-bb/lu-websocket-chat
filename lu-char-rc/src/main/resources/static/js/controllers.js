'use strict';

/* Controllers */

angular.module('springChat.controllers', ['toaster'])
	.controller('ChatController', ['$scope', '$location', '$interval', 'toaster', 'ChatSocket', 
		function($scope, $location, $interval, toaster, chatSocket) {
		  
		
		$scope.messages = [];
		
		$scope.topics=[
			{name:'MEDICAL'},
			{name:'EDUCATIONS'},
			{name:'POLITICS'},
			{name:'SCIENCE'}
		];
		  
		$scope.selectedtopics ={
			names: {}
		};
		
		$scope.subscribe=function(){
			
			chatSocket.connect(function() {
				console.log($scope.userName); 
  			});

			angular.forEach($scope.selectedtopics.names, function(value, key){
          		var wsURL='/app/'+key;
          		chatSocket.subscribe(wsURL, function(message) {
					console.log(message); 
				});
        	});

			

		}

		var initStompClient = function() {
			chatSocket.init('/ws');
		};
		  
		initStompClient();
	}]);
	