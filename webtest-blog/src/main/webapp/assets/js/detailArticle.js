/**
 * lqwlqw@mywiz.cn
 */

var app = angular.module('myApp', []);
app.controller('formCtrl', function($scope, $http) {
	$scope.master = {
		title : "title",
		content : "detail"
	};
	$scope.reset = function() {
		$scope.article = angular.copy($scope.master);
		var articleId = request.QueryString("id");
		$http({
			method : 'POST',
			url : '../getArticleById.do',
			params : {
				'articleId' : articleId
			}
		}).success(function(response) {
			$scope.article = response.result;
		});
	};
	$scope.addArticle = function() {
		$http({
			method : 'POST',
			url : '../modifyArticle.do',
			params : {
				'json' : $scope.article
			},
			headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8',
					"Accept": "application/json;charset=utf-8",
			       "Accept-Charset":"charset=utf-8"},
			
			}).success(function(response) {
				alert(response);
				window.history.back();
			});
		
	};
	$scope.reset();
}).filter(  
	    'to_trusted', ['$sce', function ($sce) {  
	        return function (text) {  
	            return $sce.trustAsHtml(text);  
	        }  
	    }]  
);
