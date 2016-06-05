'use strict';
var hsmAssetSearchApp= angular.module('myApp','')

hsmAssetSearchApp.controller('hsmAssetSearchController', function($scope,$http,$location,$rootScope,searchAstData) {



	$rootScope.$on('CallAstParentMethod', function(event, data) {
    	$scope.searchAstChild(data);
 	 });

	 $scope.searchAstChild=function(data)
    {
			$http({
				url: 'http://asmapp.cloudapp.net/hsm/secure/assetSearch',
				method: "POST",
				data: $rootScope.searchAstParameter,
				crossDomain:true,
				headers:{
					'Authorization':$rootScope.jwtTokenVal
				}
			}).success(function (response) {
					if(data=='AstDeleteFailure'){
						$scope.deleteAstHide=true;
					}else{
						$scope.deleteAstHide=false;
					}
					$rootScope.asstLstValue= response;
					searchAstData.store(response,$scope);
					$rootScope.showComputerSearchResults = true;
					$scope.serviceErrorHide=false;
					$scope.errorfield = '';
					$location.path('/computers');
				}).error(function (response) {
					$scope.serviceErrorHide=true;
					$rootScope.asstLstValue= '';
					$scope.errorfield = 'field-label-error';
					$rootScope.showComputerSearchResults = false;
					$location.path('/computers');
			});
    }



    $scope.searchAst=function()
    {	
		var emplID = $scope.empId;
		var comName = $scope.assetName;
		var opeSyst = $scope.operatingSys;
		var serNumber = $scope.serialNo;
		if((emplID != undefined && emplID.length >= 1) || (comName != undefined && comName.length >= 1) || (opeSyst != undefined && opeSyst.length >= 1) || (serNumber != undefined && serNumber.length >= 1)) {
			$scope.showComputerSearchResults = false;
			$scope.showComputerSearchResults = $scope.showComputerSearchResults ? false : true;
			$scope.errorHide = false;
			$scope.errorfield = '';
			var parameter = JSON.stringify({type:"assests",empId:$scope.empId,assetName:$scope.assetName,
				operatingSys:$scope.operatingSys,serialNo:$scope.serialNo});
			$rootScope.searchAstParameter=parameter;
			$http({
				url: 'http://asmapp.cloudapp.net/hsm/secure/assetSearch',
				method: "POST",
				data: parameter,
				crossDomain:true,
				headers:{
					'Authorization':$rootScope.jwtTokenVal
				}
			}).success(function(response){
				$rootScope.asstLstValue= response;
				searchAstData.store(response,$scope);
				$rootScope.showComputerSearchResults = true;
				$scope.errorHide = false;
				$scope.serviceErrorHide=false;
				$scope.errorfield = '';
			}).error(function(response){
				$scope.serviceErrorHide=true;
				$scope.errorHide = false;
				$rootScope.asstLstValue= '';
				$scope.errorfield = 'field-label-error';
				$rootScope.showComputerSearchResults = false;
				$location.path('/computers');
			});
		} else {
			$rootScope.showComputerSearchResults = false;
			$scope.errorHide = true;
			$scope.errorfield = 'field-label-error';
		}
    }
})


hsmAssetSearchApp.factory('searchAstData', function ($rootScope) {
    var astArray={};
    return {
        store: function (response) {
            for(var i in response)
            {
                 astArray[response[i].assetkey]=response[i];
            }
        },
        get: function (key) {
            return astArray[key];
        }
    };
});
