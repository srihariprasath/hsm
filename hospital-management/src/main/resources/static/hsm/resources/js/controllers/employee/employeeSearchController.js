'use strict';
var hsmEmpSearchApp= angular.module('myApp','')

hsmEmpSearchApp.controller('hsmEmpSearchController', function($scope,$http,$location,$rootScope,searchData) {
	

     $rootScope.$on('CallEmpParentMethod', function(event, data) {
    	$scope.searchEmployeeChild(data);
 	 });

	 $scope.searchEmployeeChild=function(data)
    {
			$http({
				url: 'http://asmapp.cloudapp.net/hsm/secure/employeesearch',
				method: "POST",
				data: $rootScope.searchParameter,
				crossDomain:true,
				headers:{
					'Authorization':$rootScope.jwtTokenVal
				}
			}).success(function (response) {
					if(data=='EmpDeleteFailure'){
						$scope.deleteEmployeeHide=true;
					}else{
						$scope.deleteEmployeeHide=false;
					}
					$rootScope.empLstValue= response;
					searchData.store(response,$scope);
					$rootScope.error="";
					$rootScope.showEmpSearchResults = true;
					$scope.errorHide = false;
					$scope.errorfield = '';
					$scope.serviceErrorHide = false;
					$location.path('/employeeDetails');
				}).error(function (response) {
					$rootScope.showEmpSearchResults = false;
					$scope.errorHide = false;
					$scope.serviceErrorHide = true;
					$rootScope.empLstValue="";
					searchData.store("",$scope);
					$location.path('/employeeDetails');
			});
    }





    $scope.searchEmployee=function()
    {
		var emplID = $scope.empId;
		var fName = $scope.empFirstName;
		var lName = $scope.empLastName;
		$scope.deleteEmployeeHide=false;
		$scope.editEmployeeHide=false;
		if((emplID != undefined && emplID.length >= 1) || (fName != undefined && fName.length >= 1) || (lName != undefined && lName.length >= 1)) {
			var parameter = JSON.stringify({type:"employee",empId:$scope.empId,empFirstName:$scope.empFirstName,
            empLastName:$scope.empLastName});
			 $rootScope.searchParameter=parameter;
			$http({
				url: 'http://asmapp.cloudapp.net/hsm/secure/employeesearch',
				method: "POST",
				data: parameter,
				crossDomain:true,
				headers:{
					'Authorization':$rootScope.jwtTokenVal
				}
			}).success(function (response) {
					$rootScope.empLstValue= response;
					searchData.store(response,$scope);
					$rootScope.error="";
					$rootScope.showEmpSearchResults = true;
					$scope.errorHide = false;
					$scope.errorfield = '';
					$scope.serviceErrorHide = false;
				}).error(function (response) {
					$rootScope.showEmpSearchResults = false;
					$scope.errorHide = false;
					$scope.serviceErrorHide = true;
					$rootScope.empLstValue="";
					searchData.store("",$scope);
					$location.path('/employeeDetails');
			});
		} else {
			$rootScope.showEmpSearchResults = false;
			$scope.errorHide = true;
			$scope.errorfield = 'field-label-error';
		}
    }
})


hsmEmpSearchApp.factory('searchData', function ($rootScope) {
    var empArray={};
    return {
        store: function (response) {
            for(var i in response)
            {
                 empArray[response[i].empId]=response[i];
            }
        },
        get: function (key) {
            return empArray[key];
        }
    };
});
