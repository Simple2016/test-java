/**
 * lqwlqw@mywiz.cn
 */
angular.module('articleApp', [])
		.controller('articlesCtrl', function($scope, $http) {
				
			$scope.delArticle = function(id) {			
				if(confirm("?")){
					 $http({
						method : 'POST',
						url : './delArticleById.do',
						params : {
							'Id' : id
						},
						headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8',
								"Accept": "application/json;charset=utf-8",
						       "Accept-Charset":"charset=utf-8"},
						
						}).success(function(response) {
							alert(response);
							location.reload();
						}); 
				}
				
			};
			$scope.search = function(g) {
				$scope.g=g; 
				 $http({
						method : 'POST',
						url : './index.do',
						params : {
							search:$scope.s,
							group:$scope.g,
							pageNumber:$scope.pageNumber
						},
						headers: { 'Content-Type': 'application/x-www-form-urlencoded; charset=utf-8',
								"Accept": "application/json;charset=utf-8",
						       "Accept-Charset":"charset=utf-8"},
						
						}).success(function(response) {
							$scope.articles = response.result;
							$scope.pageNumber =response.pageNumber;
							$scope.pageCount =response.pageCount;
							$scope.logined =response.logined;
						}); 
			};
			
			$scope.previousPage= function() {	
				if($scope.pageNumber>0){					
					--$scope.pageNumber;
					$scope.search();
					$("html,body").animate({ scrollTop: 0 }, 500)
				}				
			};
			$scope.nextPage= function() {
				if($scope.pageNumber+1<$scope.pageCount){
					++$scope.pageNumber;
					$scope.search();
					$("html,body").animate({ scrollTop: 0 }, 500)
				}				
			};

			$scope.search();
			
		})
		.filter(  
		   'to_trusted', ['$sce', function ($sce) {  
		       return function (text) {  
		           return $sce.trustAsHtml(text);  
		       }  
		   }]  
		);
 
$(function () {
	 var str = window.location.href;
	 str = str.substring(str.lastIndexOf("/") + 1);
	 if(str==""){
		 str="index.html"; 
	 }
	 if ($.cookie(str)) {
		 $("html,body").animate({ scrollTop: $.cookie(str) }, 500);
	 }
	 else {
	 }
});
	 
$(window).scroll(function () {
	 var str = window.location.href;
	 str = str.substring(str.lastIndexOf("/") + 1);
	 if(str==""){
		 str="index.html"; 
	 }
	 var top = $(document).scrollTop();
	 $.cookie(str, top, { path: '/' });
	 return $.cookie(str);
});

	    
	    
	    