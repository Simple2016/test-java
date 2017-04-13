/**
 * lqwlqw@mywiz.cn
 */
var editor;
KindEditor.ready(function(K) {
	editor = K.create('textarea[name="content"]', {
		allowFileManager : true
	});				
});
var app = angular.module('myApp', ['templates']);
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
					editor.appendHtml($scope.article.detail);
					if($scope.article.groupList!=null){
						$scope.groups=$scope.article.groupList
					}
				});
			};
			$scope.addArticle = function() {

				$scope.article.detail=editor.html();
				$http({
					method : 'POST',
					url : '../modifyArticle.do',
					params : {
						'json' : $scope.article,
						'group' : angular.toJson($scope.groups)
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
			
			$http.post("../queryGroup.do").success(function(response) {
				$scope.groupList = response.result;
			});
			
			$scope.selectItemCallback = function(item){
			    $scope.selectedItem = item;
			};

			$scope.removeItemCallback = function(item){
			    $scope.removedItem = item;
			};

			$scope.onSubmit = function () {
			    console.log("submit");
			    if($scope.multipleSelectForm.$invalid){
			        if($scope.multipleSelectForm.$error.required != null){
			            $scope.multipleSelectForm.$error.required.forEach(function(element){
			                element.$setDirty();
			            });
			        }
			        return null;
			    }
			    alert("valid field");
			};
		});