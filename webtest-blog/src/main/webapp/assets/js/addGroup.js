	
/**
 * lqwlqw@mywiz.cn
 */
var app = angular.module('myApp', []);
		app.controller('formCtrl', function($scope, $http) {			
			$scope.group = {
					groupName : "test"		
			};
			$scope.addGroup = function() {
				$http({
					method : 'POST',
					url : '../addGroup.do',
					params : {
						'groupName' : $scope.group.groupName
					},
					headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8',
							"Accept": "application/json;charset=utf-8",
					       "Accept-Charset":"charset=utf-8"},
					
					}).success(function(response) {						
						alert(response);
						history.back(-1);	
					});
				
			};
		});