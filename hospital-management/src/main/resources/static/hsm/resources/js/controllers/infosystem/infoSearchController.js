'use strict';
var hsmInfoSearchApp= angular.module('myApp','')

hsmInfoSearchApp.controller('hsmInfoSearchController', function($scope,$http,$location,$rootScope,searchInfoData) {
	
	 $rootScope.$on('CallInfoParentMethod', function(event, data) {
    	$scope.searchInfoChild(data);
 	 });

	 $scope.searchInfoChild=function(data)
    {
			searchInfoData.store('',$scope);
			$http({
				url: 'http://asmapp.cloudapp.net/hsm/secure/infoSearch',
				method: "POST",
				data:  $rootScope.searchInfoParameter,
				crossDomain:true,
				headers:{
					'Authorization':$rootScope.jwtTokenVal
				}
			}).success(function(response){
				if(data=='InfoDeleteFailure'){
						$scope.deleteInfoHide=true;
				}else{
						$scope.deleteInfoHide=false;
				}
				$rootScope.infoLstValue= response;
				searchInfoData.store(response,$scope);
				$rootScope.error="";
				$rootScope.showInfoSearchResults = true;
				$scope.errorHide = false;
				$scope.serviceErrorHide = false;
				$location.path('/infoSystems');
			}).error(function(response){
				$rootScope.showInfoSearchResults = false;
				$scope.errorHide = false;
				$scope.serviceErrorHide = true;
				$rootScope.infoLstValue="";
				searchInfoData.store("",$scope);
				$location.path('/infoSystems');
			});
    }




    $scope.searchInfo=function()
    {
        $rootScope.error="";
        $rootScope.successMsg="";
		var employeeId = $scope.empId;
		var systemName = $scope.sysName;
		var admin = $scope.administrator;
		var bkAdmin = $scope.backupAdministrator;
		if((employeeId != undefined && employeeId.length >= 1) || (systemName != undefined && systemName.length >= 1) || (admin != undefined && admin.length >= 1) || (bkAdmin != undefined && bkAdmin.length >= 1)) {
			$scope.errorfield = '';
			searchInfoData.store('',$scope);
			var parameter = JSON.stringify({type:"information",empId:$scope.empId,sysName:$scope.sysName,
            BackupAdministrator:$scope.backupAdministrator,administrator:$scope.administrator});
            $rootScope.searchInfoParameter=parameter;
			$http({
				url: 'http://asmapp.cloudapp.net/hsm/secure/infoSearch',
				method: "POST",
				data: parameter,
				crossDomain:true,
				headers:{
					'Authorization':$rootScope.jwtTokenVal
				}
			}).success(function(response){
				$rootScope.infoLstValue= response;
				searchInfoData.store(response,$scope);
				$rootScope.showInfoSearchResults = true;
				$scope.errorHide = false;
				$scope.errorfield = '';
				$scope.serviceErrorHide = false;
				$location.path('/infoSystems');

			}).error(function(response){
				$rootScope.showInfoSearchResults = false;
				$scope.errorHide = false;
				$scope.serviceErrorHide = true;
				$rootScope.infoLstValue="";
				searchInfoData.store("",$scope);
				$location.path('/infoSystems');
			});
		} else {
			$rootScope.showInfoSearchResults = false;
			$scope.errorHide = true;
			$scope.errorfield = 'field-label-error';
		}
    }


})

hsmInfoSearchApp.factory('searchInfoData', function ($rootScope) {
    var infoArray={};
    return {
        store: function (response) {
            for(var i in response)
            {
                 infoArray[response[i].adminID]=response[i];
            }
        },
        get: function (key) {
            return infoArray[key];
        }
    };
});
